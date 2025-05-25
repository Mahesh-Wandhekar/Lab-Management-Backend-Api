package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.StdEntity;

public interface StdTblRepo extends JpaRepository<StdEntity, Integer> {

	
	public StdEntity findByTblNum(Integer id);
	
	
	
}
