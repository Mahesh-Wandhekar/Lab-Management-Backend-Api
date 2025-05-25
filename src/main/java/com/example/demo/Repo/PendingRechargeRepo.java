package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PendingRechargeEntity;
import com.example.demo.Entity.StdEntity;

public interface PendingRechargeRepo  extends JpaRepository<PendingRechargeEntity, Integer>{

	
	PendingRechargeEntity findByStdEntityDetails(StdEntity stdEntity);

	
	
}
