package com.example.demo.Entity;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class LabTbl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tblId;
	private Integer tblNum;
	private String tblStatus;
	
	@CreationTimestamp
	private LocalDate  tblCreateDate;
	
	
	@OneToOne(mappedBy = "labTblDetails",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonBackReference
	private StdEntity stdEntityDetails;

	
	
	public Integer getTblId() {
		return tblId;
	}

	public void setTblId(Integer tblId) {
		this.tblId = tblId;
	}

	public Integer getTblNum() {
		return tblNum;
	}

	public void setTblNum(Integer tblNum) {
		this.tblNum = tblNum;
	}

	public String getTblStatus() {
		return tblStatus;
	}

	public void setTblStatus(String tblStatus) {
		this.tblStatus = tblStatus;
	}

	public LocalDate getTblCreateDate() {
		return tblCreateDate;
	}

	public void setTblCreateDate(LocalDate tblCreateDate) {
		this.tblCreateDate = tblCreateDate;
	}

	public StdEntity getStdEntityDetails() {
		return stdEntityDetails;
	}

	public void setStdEntityDetails(StdEntity stdEntityDetails) {
		this.stdEntityDetails = stdEntityDetails;
	}
	
	
	
	
}
