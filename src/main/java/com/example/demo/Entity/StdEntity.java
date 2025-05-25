package com.example.demo.Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class StdEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stdId;
	private String stdName;
	private String phone;
	private String adharNo;
	private String planStatus;
	private Integer tblNum;
	private String profileStatus;
	
	@CreationTimestamp
	private LocalDate AppDate;
	
	@CreationTimestamp
	private LocalDate PlanStartDate;
	
	
	private LocalDate PlanEndDate;
	
	@OneToOne
	@JoinColumn(name="tbl_Id")
	@JsonManagedReference
	private LabTbl labTblDetails;
	
	
	@OneToMany(mappedBy = "stdEntityDetails",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<PaymentEntity> paymentEntitiesDetails;
	
	
	@OneToMany(mappedBy = "stdEntityDetails",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<PendingRechargeEntity> pendingRechargeEntities;
	

	public Integer getStdId() {
		return stdId;
	}

	public void setStdId(Integer stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public Integer getTblNum() {
		return tblNum;
	}

	public void setTblNum(Integer tblNum) {
		this.tblNum = tblNum;
	}

	public LocalDate getAppDate() {
		return AppDate;
	}

	public void setAppDate(LocalDate appDate) {
		AppDate = appDate;
	}

	public LocalDate getPlanStartDate() {
		return PlanStartDate;
	}

	public void setPlanStartDate(LocalDate planStartDate) {
		PlanStartDate = planStartDate;
	}

	public LocalDate getPlanEndDate() {
		return PlanEndDate;
	}

	public void setPlanEndDate(LocalDate localDate) {
		PlanEndDate = localDate;
	}

	public LabTbl getLabTblDetails() {
		return labTblDetails;
	}

	public void setLabTblDetails(LabTbl labTblDetails) {
		this.labTblDetails = labTblDetails;
	}

	public List<PaymentEntity> getPaymentEntitiesDetails() {
		return paymentEntitiesDetails;
	}

	public void setPaymentEntitiesDetails(List<PaymentEntity> paymentEntitiesDetails) {
		this.paymentEntitiesDetails = paymentEntitiesDetails;
	}

	public List<PendingRechargeEntity> getPendingRechargeEntities() {
		return pendingRechargeEntities;
	}

	public void setPendingRechargeEntities(List<PendingRechargeEntity> pendingRechargeEntities) {
		this.pendingRechargeEntities = pendingRechargeEntities;
	}

	public String getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}

	
	
	

	
	
	
	
}
