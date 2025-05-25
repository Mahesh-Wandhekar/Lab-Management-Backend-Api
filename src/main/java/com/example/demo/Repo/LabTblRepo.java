package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.LabTbl;

public interface LabTblRepo  extends JpaRepository<LabTbl, Integer>{

	public List<LabTbl> findByTblStatus(String status);
	
	public LabTbl findByTblNum(Integer tblNum);
	
	
	
}
