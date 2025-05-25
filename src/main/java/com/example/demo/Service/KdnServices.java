package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Bind.KdnBind;
import com.example.demo.Entity.KdnEntity;
import com.example.demo.Repo.KdnRepo;

@Service
public class KdnServices {

	@Autowired
	private KdnRepo kdnRepo;
	
	public boolean reg(KdnBind kdnBind) {	
		KdnEntity kdnEntity=new KdnEntity();
		kdnEntity.setAccNo(kdnBind.getAccNo());
		kdnEntity.setAccName(kdnBind.getAccName());
		kdnEntity.setaBal(kdnBind.getaBal());
		kdnRepo.save(kdnEntity);
		return true;
	}
	
	
	public KdnEntity search(KdnBind kdnBind) {
	 return	kdnRepo.findByAccNo(kdnBind.getAccNo());
	}
	
	
	public boolean delete(KdnBind kdnBind) {
	    KdnEntity kdnEntity = kdnRepo.findByAccNo(kdnBind.getAccNo());
	    if (kdnEntity != null) {
	        kdnRepo.deleteById(kdnEntity.getaId());
	        return true;
	    }
	    return false;
	}

	
}
