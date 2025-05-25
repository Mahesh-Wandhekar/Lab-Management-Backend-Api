package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.KdnEntity;

public interface KdnRepo extends JpaRepository<KdnEntity, Integer>{
	
	public KdnEntity findByAccNo(String AccNo);

}
