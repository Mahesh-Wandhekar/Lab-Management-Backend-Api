package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PaymentEntity;
import com.example.demo.Entity.StdEntity;

public interface PaymentRepo extends JpaRepository<PaymentEntity, Integer> {

	public List<PaymentEntity> findBystdEntityDetails(StdEntity stdentity);
	
}
