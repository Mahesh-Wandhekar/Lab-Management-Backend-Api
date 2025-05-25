package com.example.demo.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Bind.LabTblBind;
import com.example.demo.Bind.PaymentBind;
import com.example.demo.Bind.StdBind;
import com.example.demo.Entity.LabTbl;
import com.example.demo.Entity.PaymentEntity;
import com.example.demo.Entity.PendingRechargeEntity;
import com.example.demo.Entity.StdEntity;

public interface LabServices {

	
	//Lab Table Related Methods
	public boolean createTbl(LabTblBind labTblBind);
	
	public List<LabTbl> getAllTbl();
	
	public List<LabTbl> getBookAndAvailableTbl(String status);
	
	
	
	//Student Admission Related Method
	public boolean createRegistration(StdBind stdBind);
	
	public List<StdEntity> getAllActiveStudents();
	
	public StdEntity getStudent(Integer Id);
	
	public StdEntity getStudentOnTblNum(Integer tblNum);
	
	public boolean deleteStudent(Integer Id);
	
	public List<StdEntity> planExpiry();
	
	
	
	//Pending Payment Process Method
	public List<PendingRechargeEntity> getAllPendeningRecharge();
	
	public List<PendingRechargeEntity> getAllPendeningRechargeOnStdId(Integer Id);
	
	
	
	//Payments process Method
	public boolean paymentProcess(PaymentBind paymentBind,Integer prndingId);
	
	public List<PaymentEntity> viewAllPayments();
	
	public List<PaymentEntity> viewPaymentsOnStdId(Integer stdId );
	
	
	

	
}
