package com.example.demo.Entity;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer payId;
	private String payBy;
	private String payMode;
	private Double amount;
	
	@CreationTimestamp
	private LocalDate  payDate;
	
	
	@ManyToOne
	@JoinColumn(name="std_Id")
	@JsonBackReference
	private StdEntity stdEntityDetails;
	
	@OneToOne(mappedBy = "paymentDetails",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonManagedReference
	private PendingRechargeEntity pendingRechargeEntityDetails;

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public String getPayBy() {
		return payBy;
	}

	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	public StdEntity getStdEntityDetails() {
		return stdEntityDetails;
	}

	public void setStdEntityDetails(StdEntity stdEntityDetails) {
		this.stdEntityDetails = stdEntityDetails;
	}

	public PendingRechargeEntity getPendingRechargeEntityDetails() {
		return pendingRechargeEntityDetails;
	}

	public void setPendingRechargeEntityDetails(PendingRechargeEntity pendingRechargeEntityDetails) {
		this.pendingRechargeEntityDetails = pendingRechargeEntityDetails;
	}
	
	
	
}
