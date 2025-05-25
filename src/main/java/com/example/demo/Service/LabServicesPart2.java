package com.example.demo.Service;

import com.example.demo.Bind.EmailBind;

import jakarta.servlet.http.HttpServletResponse;

public interface LabServicesPart2 {

	//Daily Process Task
		public void dailyProcessTask();
		
		
		public boolean pdfAllPendingRecharges(HttpServletResponse httpServletResponse,EmailBind emailBind);
		
		public boolean pdfAllAddmission(HttpServletResponse httpServletResponse,EmailBind emailBind);
		
		
}
