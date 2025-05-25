package com.example.demo.Controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Bind.AdminBind;
import com.example.demo.Bind.AdminLoginBind;
import com.example.demo.Bind.ForgetPwd;
import com.example.demo.Service.AdminServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminRestController {

	@Autowired
	private AdminServices adminServices;

	@PostMapping("adminreg")
	public ResponseEntity<String> adminRegistration(@RequestBody AdminBind adminBind) throws NoSuchAlgorithmException {
		boolean status = adminServices.adminRegistration(adminBind);
		if (status) {
			return new ResponseEntity("Admin Account Created", HttpStatus.OK);
		} else {
			return new ResponseEntity("Email Is Already Exited", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("adminlogin")
	public ResponseEntity<String> adminRegistration(@RequestBody AdminLoginBind adminLoginBind)
			throws NoSuchAlgorithmException {
		boolean status = adminServices.adminLogin(adminLoginBind);
		if (status) {
			return new ResponseEntity("true", HttpStatus.OK);
		} else {
			return new ResponseEntity("false", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/forgetpwd")
	public ResponseEntity<String> forgetPwd(@RequestBody ForgetPwd forgetPwd) throws NoSuchAlgorithmException {

	    // Use .equals() for string content comparison
	    if (!forgetPwd.getNewPazzword().equals(forgetPwd.getConfirmPazzword())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("New Password and Confirm Password must be the same.");
	    }

	    boolean status = adminServices.forgetPwd(forgetPwd);
	    if (status) {
	        return ResponseEntity.ok("true");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("false");
	    }
	}


}
