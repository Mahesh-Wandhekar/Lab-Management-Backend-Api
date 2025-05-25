package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Bind.EmailBind;
import com.example.demo.Service.LabServicesPart2;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LabRestController2 {

	@Autowired
	private LabServicesPart2  labServicesPart2;
	
	
	@PostMapping("exportpendingrechargepdf")
	public ResponseEntity<String> exportAllPendingRechargePdf(HttpServletResponse response,@RequestBody EmailBind emailBind) throws Exception{
		response.setContentType("application/pdf");
    	response.addHeader("content-Disposition", "attachment;filename=AllPendingRechargesData.pdf");
		boolean status=labServicesPart2.pdfAllPendingRecharges(response, emailBind);
		if(status) {
			return new ResponseEntity<String>("true",HttpStatus.OK);
		}
		return new ResponseEntity<String>("false",HttpStatus.OK);
	}
	
	
	@PostMapping("exportalladdmissionpdf")
	public ResponseEntity<String> exportAllAddmissionPdf(HttpServletResponse response,@RequestBody EmailBind emailBind) throws Exception{
		response.setContentType("application/pdf");
    	response.addHeader("content-Disposition", "attachment;filename=AllAddmissionsData.pdf");
		boolean status=labServicesPart2.pdfAllAddmission(response, emailBind);
		if(status) {
			return new ResponseEntity<String>("true",HttpStatus.OK);
		}
		return new ResponseEntity<String>("false",HttpStatus.OK);
	}
	
	
	
	
}
