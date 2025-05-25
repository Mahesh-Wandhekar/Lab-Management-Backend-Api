package com.example.demo.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class PendingRechargeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pendingId;
	
	private String StdName;
	
	private Double pendingAmount;
	
	private String status;
	
	@CreationTimestamp
	private LocalDate pendingDate;
	
	
	@ManyToOne
	@JoinColumn(name="std_Id")
	@JsonBackReference
	private StdEntity stdEntityDetails;

	@OneToOne
	@JoinColumn(name="pay_id")
	@JsonBackReference
	private PaymentEntity paymentDetails;
	
	
	public Integer getPendingId() {
		return pendingId;
	}

	public void setPendingId(Integer pendingId) {
		this.pendingId = pendingId;
	}

	public Double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(Double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public StdEntity getStdEntityDetails() {
		return stdEntityDetails;
	}

	public void setStdEntityDetails(StdEntity stdEntityDetails) {
		this.stdEntityDetails = stdEntityDetails;
	}

	public LocalDate getPendingDate() {
		return pendingDate;
	}

	public void setPendingDate(LocalDate pendingDate) {
		this.pendingDate = pendingDate;
	}

	public PaymentEntity getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentEntity paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStdName() {
		return StdName;
	}

	public void setStdName(String stdName) {
		StdName = stdName;
	}

	
	
	
	
}
