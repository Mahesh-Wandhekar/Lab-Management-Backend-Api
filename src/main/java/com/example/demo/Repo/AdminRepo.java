package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.Entity.LabAdminEntity;

public interface AdminRepo extends JpaRepository<LabAdminEntity, Integer>{
	
	public LabAdminEntity findByEmailAndPazzword(String email,String pazzword);
	
	public LabAdminEntity findByEmail(String email);
	

}
