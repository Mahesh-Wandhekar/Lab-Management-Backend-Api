package com.example.demo.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Bind.AdminBind;
import com.example.demo.Bind.AdminLoginBind;
import com.example.demo.Bind.ForgetPwd;
import com.example.demo.Entity.LabAdminEntity;
import com.example.demo.Repo.AdminRepo;

@Service
public class AdminServicesImp implements AdminServices {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public boolean adminLogin(AdminLoginBind adminLoginBind) throws NoSuchAlgorithmException {

		String pwd = adminLoginBind.getPazzword();
		MessageDigest msgdigest = MessageDigest.getInstance("SHA-256");
		msgdigest.reset();
		msgdigest.update(pwd.getBytes());
		byte[] msgpwd = msgdigest.digest();
		byte[] encodepwd = Base64.getEncoder().encode(msgpwd);

		LabAdminEntity adminEntity = adminRepo.findByEmailAndPazzword(adminLoginBind.getEmail(), new String(encodepwd));
		if (adminEntity == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean adminRegistration(AdminBind adminBind) throws NoSuchAlgorithmException {
		LabAdminEntity entity = adminRepo.findByEmail(adminBind.getEmail());
		if (entity != null) {
			return false;
		}
		LabAdminEntity adminEntity = new LabAdminEntity();
		BeanUtils.copyProperties(adminBind, adminEntity);
		String pwd = adminBind.getPazzword();

		MessageDigest msgdigest = MessageDigest.getInstance("SHA-256");
		msgdigest.reset();
		msgdigest.update(pwd.getBytes());
		byte[] pwd1 = msgdigest.digest();
		byte[] ecodepwd = Base64.getEncoder().encode(pwd1);
		adminEntity.setPazzword(new String(ecodepwd));
		adminRepo.save(adminEntity);
		return true;
	}

	@Override
	public boolean forgetPwd(ForgetPwd forgetPwd) throws NoSuchAlgorithmException {
		LabAdminEntity adminEntity = adminRepo.findByEmail(forgetPwd.getEmail());
		if(null!=adminEntity) {
			String pwd=forgetPwd.getNewPazzword();
			MessageDigest msgdigest = MessageDigest.getInstance("SHA-256");
			msgdigest.reset();
			msgdigest.update(pwd.getBytes());
			byte[] pwd1 = msgdigest.digest();
			byte[] ecodepwd = Base64.getEncoder().encode(pwd1);
			adminEntity.setPazzword(new String(ecodepwd));
			adminRepo.save(adminEntity);
			return true;
			
		}
		
		return false;
	}

}
