package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Bind.LabTblBind;
import com.example.demo.Bind.PaymentBind;
import com.example.demo.Bind.StdBind;
import com.example.demo.Entity.LabTbl;
import com.example.demo.Entity.PaymentEntity;
import com.example.demo.Entity.PendingRechargeEntity;
import com.example.demo.Entity.StdEntity;
import com.example.demo.Repo.LabTblRepo;
import com.example.demo.Repo.PaymentRepo;
import com.example.demo.Repo.PendingRechargeRepo;
import com.example.demo.Repo.StdTblRepo;

@Service
public class LabServicesImp implements LabServices {

	@Autowired
	private LabTblRepo labTblRepo;

	@Autowired
	private StdTblRepo stdTblRepo;

	@Autowired
	private PendingRechargeRepo rechargeRepo;

	@Autowired
	private PaymentRepo paymentRepo;

	// Add The Table
	@Override
	public boolean createTbl(LabTblBind labTblBind) {
		
		LabTbl labTbl1 =labTblRepo.findByTblNum(labTblBind.getTblNum());
		if(labTbl1==null) {
			LabTbl labTbl = new LabTbl();
			BeanUtils.copyProperties(labTblBind, labTbl);
			labTbl.setTblStatus("Available");
			labTblRepo.save(labTbl);
			return true;	
		}
		return false;
		
	}

	// Get All Table
	@Override
	public List<LabTbl> getAllTbl() {
		return labTblRepo.findAll();
	}

	// Get Available table and Booked Table
	@Override
	public List<LabTbl> getBookAndAvailableTbl(String status) {
		return labTblRepo.findByTblStatus(status);
	}

	// Student Application Registration
	@Override
	public boolean createRegistration(StdBind stdBind) {
		StdEntity stdEntity = new StdEntity();
		BeanUtils.copyProperties(stdBind, stdEntity);
		stdEntity.setPlanStatus("Expiry");
		stdEntity.setProfileStatus("Active");
		stdEntity.setPlanEndDate(LocalDate.now().plusMonths(1));

		LabTbl labTbl = labTblRepo.findByTblNum(stdBind.getTblNum());
		if (labTbl!=null) {	
			labTbl.setTblStatus("Book");
			stdEntity.setLabTblDetails(labTbl);
			labTblRepo.save(labTbl);
		}

		StdEntity savedStdEntity = stdTblRepo.save(stdEntity);

		PendingRechargeEntity rechargeEntity = new PendingRechargeEntity();
		rechargeEntity.setPendingAmount(500.00);
		rechargeEntity.setStatus("Pending");
		rechargeEntity.setStdName(stdEntity.getStdName());
		rechargeEntity.setStdEntityDetails(stdEntity);
		rechargeRepo.save(rechargeEntity);

		List<PendingRechargeEntity> rechargeList = new ArrayList<>();
		rechargeList.add(rechargeEntity);
		savedStdEntity.setPendingRechargeEntities(rechargeList);

		return true;
	}

	// Get All Registerd Students
	@Override
	public List<StdEntity> getAllActiveStudents() {
		List<StdEntity> entities = stdTblRepo.findAll();
		List<StdEntity> filterdData = entities.stream().filter(e -> e.getProfileStatus().equals("Active"))
				.collect(Collectors.toList());
		return filterdData;
	}

	// Get Student Based On Id
	@Override
	public StdEntity getStudent(Integer Id) {
		Optional<StdEntity> optional = stdTblRepo.findById(Id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	// Delete The Student Addmission
	@Override
	public boolean deleteStudent(Integer id) {
		Optional<StdEntity> optional = stdTblRepo.findById(id);

		if (optional.isPresent()) {
			StdEntity stdEntity = optional.get();

			// Set lab status to "Available"
			LabTbl labTbl = stdEntity.getLabTblDetails();
			if (labTbl != null) {
				labTbl.setTblStatus("Available");
				labTblRepo.save(labTbl);
			}

			
				stdEntity.setProfileStatus("Inactive");
				stdEntity.setLabTblDetails(null);
				stdEntity.setTblNum(0);
				stdTblRepo.save(stdEntity);
				return true;
			
		}

		return false;
	}

	// Get The StudentBased On The Tble Num
	@Override
	public StdEntity getStudentOnTblNum(Integer tblNum) {
			return stdTblRepo.findByTblNum(tblNum);
	}

	//Get The Plan Expiry Student
	@Override
	public List<StdEntity> planExpiry() {
		List<StdEntity> entities = stdTblRepo.findAll();
		List<StdEntity> filterd = entities.stream().filter(e -> e.getPlanStatus().equals("Expiry"))
				.collect(Collectors.toList());
		return filterd;
	}

	// Get The All Pending Recharges
	@Override
	public List<PendingRechargeEntity> getAllPendeningRecharge() {
		List<PendingRechargeEntity> pendingRechargeEntities = rechargeRepo.findAll();
		List<PendingRechargeEntity> filterdData = pendingRechargeEntities.stream()
				.filter(e -> e.getStatus().equals("Pending")).collect(Collectors.toList());
		return filterdData;
	}

	// Get The Pending Recharge Based On Std ID
	@Override
	public List<PendingRechargeEntity> getAllPendeningRechargeOnStdId(Integer Id) {
		Optional<StdEntity> optional = stdTblRepo.findById(Id);
		if (optional.isPresent()) {
			StdEntity stdEntity = optional.get();
			List<PendingRechargeEntity> entities = stdEntity.getPendingRechargeEntities();
			List<PendingRechargeEntity> filterdData = entities.stream().filter(e -> e.getStatus().equals("Pending"))
					.collect(Collectors.toList());
			return filterdData;
		}
		return null;
	}

	// Done The Payment Process
	@Override
	public boolean paymentProcess(PaymentBind paymentBind, Integer pendingId) {

		Optional<PendingRechargeEntity> optional = rechargeRepo.findById(pendingId);
		if (optional.isPresent()) {
			PendingRechargeEntity pendingRechargeEntity = optional.get();
			StdEntity stdEntity = pendingRechargeEntity.getStdEntityDetails();
			stdEntity.setPlanStatus("Valid");
			PaymentEntity paymentEntity = new PaymentEntity();
			BeanUtils.copyProperties(paymentBind, paymentEntity);

			paymentEntity.setPendingRechargeEntityDetails(pendingRechargeEntity);
			paymentEntity.setStdEntityDetails(stdEntity);
			paymentEntity.setPendingRechargeEntityDetails(pendingRechargeEntity);

			pendingRechargeEntity.setStatus("Success");
			rechargeRepo.save(pendingRechargeEntity);
			paymentRepo.save(paymentEntity);
			return true;
		}

		return false;
	}

	// Get The All History Of The Payments
	@Override
	public List<PaymentEntity> viewAllPayments() {
		return paymentRepo.findAll();
	}

	// Get The PAyment History Based On Student Id
	@Override
	public List<PaymentEntity> viewPaymentsOnStdId(Integer stdId) {
		Optional<StdEntity> optional = stdTblRepo.findById(stdId);
		if (optional.isPresent()) {
			StdEntity stdEntity = optional.get();
			List<PaymentEntity> paymentEntities = paymentRepo.findBystdEntityDetails(stdEntity);
			return paymentEntities;
		}

		return null;

	}

	

}
