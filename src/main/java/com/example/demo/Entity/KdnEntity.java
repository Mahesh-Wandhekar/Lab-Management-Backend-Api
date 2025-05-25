package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KdnEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer aId;
	private String accNo;
	private String accName;
	private Double aBal;
	
	public Integer getaId() {
		return aId;
	}
	public void setaId(Integer id) {
		aId = id;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Double getaBal() {
		return aBal;
	}
	public void setaBal(Double aBal) {
		this.aBal = aBal;
	}
	
	
	
	
	
	
}
