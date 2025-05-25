package com.example.demo.Service;

import java.security.NoSuchAlgorithmException;

import com.example.demo.Bind.AdminBind;
import com.example.demo.Bind.AdminLoginBind;
import com.example.demo.Bind.ForgetPwd;

public interface AdminServices {

	
	public boolean adminRegistration(AdminBind adminBind) throws NoSuchAlgorithmException;
	
	public boolean adminLogin(AdminLoginBind adminLoginBind) throws NoSuchAlgorithmException;
	
	public boolean forgetPwd(ForgetPwd forgetPwd)throws NoSuchAlgorithmException;
	
	
}
