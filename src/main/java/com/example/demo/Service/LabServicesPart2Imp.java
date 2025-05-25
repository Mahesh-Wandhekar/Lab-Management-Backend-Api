package com.example.demo.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.Bind.EmailBind;
import com.example.demo.Entity.PendingRechargeEntity;
import com.example.demo.Entity.StdEntity;
import com.example.demo.Repo.LabTblRepo;
import com.example.demo.Repo.PaymentRepo;
import com.example.demo.Repo.PendingRechargeRepo;
import com.example.demo.Repo.StdTblRepo;
import com.example.demo.Utility.EmailUtility;
import com.example.demo.Utility.GeneratePDFAllAdmission;
import com.example.demo.Utility.GeneratePFDReportForPendingRecharges;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class LabServicesPart2Imp implements LabServicesPart2 {

	@Autowired
	private LabTblRepo labTblRepo;

	@Autowired
	private StdTblRepo stdTblRepo;

	@Autowired
	private PendingRechargeRepo rechargeRepo;

	@Autowired
	private PaymentRepo paymentRepo;
	
	
	
	@Autowired
	private GeneratePFDReportForPendingRecharges forPendingRecharges;
	
	
	@Autowired
	private GeneratePDFAllAdmission pdfAllAdmission;
	
	
	@Autowired
	private EmailUtility emailUtility;

	// Daily Execute The Tas At 9Am
	@Transactional
	@Scheduled(cron = "0 0 10 * * ?")
	public void dailyProcessTask() {
	    List<StdEntity> stdEntities = stdTblRepo.findAll();
	    LocalDate today = LocalDate.now();

	    for (StdEntity entity : stdEntities) {
	        LocalDate endDate = entity.getPlanEndDate();
	        System.out.println("Checking student: " + entity.getStdName() + " | End Date: " + endDate + " | Today: " + today);

	        if (endDate != null && today.isAfter(endDate)) {
	            entity.setPlanStatus("Expiry");
	            entity.setPlanStartDate(today);
	            entity.setPlanEndDate(today.plusMonths(1));

	            StdEntity savedStdEntity = stdTblRepo.save(entity);
	            System.out.println("Updated: " + savedStdEntity.getStdName());

	            PendingRechargeEntity rechargeEntity = new PendingRechargeEntity();
	            rechargeEntity.setPendingAmount(500.00);
	            rechargeEntity.setStatus("Pending");
	            rechargeEntity.setStdName(entity.getStdName());
	            rechargeEntity.setStdEntityDetails(savedStdEntity);
	            rechargeRepo.save(rechargeEntity);
	        }
	    }

	    System.out.println("Scheduling Is Started");
	    
	}
	
	
	

	@Override
	public boolean pdfAllPendingRecharges(HttpServletResponse httpServletResponse, EmailBind emailBind) {
		
		List<PendingRechargeEntity> pendingRechargeEntities = rechargeRepo.findAll();
		List<PendingRechargeEntity> filterdData = pendingRechargeEntities.stream()
				.filter(e -> e.getStatus().equals("Pending")).collect(Collectors.toList());
		
		File file=new File("AllPendingRecharge");
		
		forPendingRecharges.GeneratePfdAllPendingRecharge(httpServletResponse, filterdData, file);
		
		String to = emailBind.getEmail();
		String subject = "All Pending Recharges Details | Lab Management";
		StringBuffer body = new StringBuffer();
		body.append("<html><body>");
		body.append("<h2>All Pending Recharges Details</h2>");
		body.append("<p>Dear Admin,</p>");
		body.append("<p>Thank you </p>");
		body.append("<p>We have sent your All Pending Recharges Details  as an attachment to this email.</p>");
		body.append("<p>Best regards,</p>");
		body.append("<p>Lab Management Team</p>");
		body.append("</body></html>");
		boolean status=emailUtility.sendAllPendingRechargePDFMail(subject, body.toString(), to, file);
		file.delete();
		if(status) {
		return true;
		}
		return false;
		
	}
	
	
	
	@Override
	public boolean pdfAllAddmission(HttpServletResponse httpServletResponse, EmailBind emailBind) {
		
		List<StdEntity> entities=stdTblRepo.findAll();
		
		List<StdEntity> filterd=entities.stream().filter(e->e.getProfileStatus().equals("Active")).collect(Collectors.toList());
		
		File file=new File("AllStudentAddmision");
		
		pdfAllAdmission.generatePdfAllStudentAdmissions(httpServletResponse, filterd, file);
		
		String to = emailBind.getEmail();
		String subject = "All Student Addmission Details | Lab Management";
		StringBuffer body = new StringBuffer();
		body.append("<html><body>");
		body.append("<h2>All Students Addmission Details</h2>");
		body.append("<p>Dear Admin,</p>");
		body.append("<p>Thank you </p>");
		body.append("<p>We have sent your All Students Addmission Details  as an attachment to this email.</p>");
		body.append("<p>Best regards,</p>");
		body.append("<p>Lab Management Team</p>");
		body.append("</body></html>");
		boolean status=emailUtility.sendAllAddmissionPDFMail(subject, body.toString(), to, file);
		file.delete();
		if(status) {
		return true;
		}
		return false;
		
	}
	
	
	
	
	

}
