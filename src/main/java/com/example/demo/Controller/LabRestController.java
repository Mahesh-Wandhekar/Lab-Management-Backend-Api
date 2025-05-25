package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Bind.LabTblBind;
import com.example.demo.Bind.PaymentBind;
import com.example.demo.Bind.StdBind;
import com.example.demo.Entity.LabTbl;
import com.example.demo.Entity.PaymentEntity;
import com.example.demo.Entity.PendingRechargeEntity;
import com.example.demo.Entity.StdEntity;
import com.example.demo.Service.LabServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LabRestController {

	@Autowired
	private LabServices labServices;

	@PostMapping("addtbl")
	public ResponseEntity<String> CreateTbl(@RequestBody LabTblBind labTblBind) {
		boolean status = labServices.createTbl(labTblBind);
		if (status) {
			return new ResponseEntity("true", HttpStatus.CREATED);
		} else {
			return new ResponseEntity("false", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("getalltbl")
	public ResponseEntity<List<LabTbl>> getAllTbl(){
		List<LabTbl> labTbls=labServices.getAllTbl();
		return new ResponseEntity(labTbls,HttpStatus.OK);
	}
	
	@GetMapping("gettbl/{status}")
	public ResponseEntity<List<LabTbl>> getTbl(@PathVariable("status")String status){
		List<LabTbl> labTbls=labServices.getBookAndAvailableTbl(status);
		return new ResponseEntity(labTbls,HttpStatus.OK);
	}

	@PostMapping("createreg")
	public ResponseEntity<String> createRegistration(@RequestBody StdBind stdBind){
		boolean status=labServices.createRegistration(stdBind);
		
		if(status) {
			return new ResponseEntity("true",HttpStatus.CREATED);
		}else {
			return new ResponseEntity("false",HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
	@GetMapping("getallstd")
	public ResponseEntity<List<StdEntity>> getAllStudents(){
		List<StdEntity>stdEntities=labServices.getAllActiveStudents();
		return new ResponseEntity(stdEntities,HttpStatus.OK);
	}
	
	@GetMapping("getstd/{stdId}")
	public ResponseEntity<StdEntity> getStudentOnId(@PathVariable("stdId") Integer stdId){
		StdEntity stdEntity=labServices.getStudent(stdId);
		return new ResponseEntity(stdEntity,HttpStatus.OK);
	}
	
	@DeleteMapping("deletestd/{stdId}")
	public ResponseEntity<String> deleteStudent(@PathVariable("stdId") Integer stdId){
		boolean status=labServices.deleteStudent(stdId);
		if(status) {
			return new ResponseEntity("true",HttpStatus.OK);			
		}
		return new ResponseEntity("",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("getstdontbl/{tblNum}")
	public ResponseEntity<StdEntity> getStudentOnTblId(@PathVariable("tblNum") Integer tblNum){
		StdEntity stdEntity=labServices.getStudentOnTblNum(tblNum);
		return new ResponseEntity(stdEntity,HttpStatus.OK);
	}
	
	@GetMapping("planexpiry")
	public ResponseEntity<StdEntity> getPlanExpiry(){
		List<StdEntity> stdEntity=labServices.planExpiry();
		return new ResponseEntity(stdEntity,HttpStatus.OK);
	}
	
	
	@GetMapping("getpendingrecharges")
	public ResponseEntity<PendingRechargeEntity> getPendingRecharges(){
		List<PendingRechargeEntity> pendingEntity=labServices.getAllPendeningRecharge();
		return new ResponseEntity(pendingEntity,HttpStatus.OK);
	}
	
	
	@GetMapping("getpendingrechargeonstd/{stdId}")
	public ResponseEntity<PendingRechargeEntity> getPendingRecharges(@PathVariable("stdId") Integer stdId){
		List<PendingRechargeEntity> pendingEntity=labServices.getAllPendeningRechargeOnStdId(stdId);
		return new ResponseEntity(pendingEntity,HttpStatus.OK);
	}
	
	@PostMapping("payment/{pendingId}")
	public ResponseEntity<String> createRegistration(@PathVariable("pendingId") Integer pendingId,@RequestBody PaymentBind paymentBind){
		boolean status=labServices.paymentProcess(paymentBind, pendingId);	
		if(status) {
			return new ResponseEntity("true",HttpStatus.CREATED);
		}else {
			return new ResponseEntity("false",HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
	
	@GetMapping("viewpayments")
	public ResponseEntity<PaymentEntity> viewAllPayment(){
		List<PaymentEntity> paymentEntity=labServices.viewAllPayments();
		return new ResponseEntity(paymentEntity,HttpStatus.OK);
	}
	
	@GetMapping("viewpayments/{stdId}")
	public ResponseEntity<PaymentEntity> viewAllPaymentOnStdId(@PathVariable("stdId") Integer stdId){
		List<PaymentEntity> paymentEntity=labServices.viewPaymentsOnStdId(stdId);
		return new ResponseEntity(paymentEntity,HttpStatus.OK);
	}
	
}
