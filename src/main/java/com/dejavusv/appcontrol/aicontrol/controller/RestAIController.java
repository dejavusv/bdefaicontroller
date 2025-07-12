package com.dejavusv.appcontrol.aicontrol.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dejavusv.appcontrol.aicontrol.entity.ServiceResult;

@RestController
public class RestAIController {
	
	@GetMapping("/"+"testconnect")
	public ResponseEntity<List<ServiceResult>> getDepartmentList() {
		List<ServiceResult> resultList =   new ArrayList<ServiceResult>();
		ServiceResult test = new ServiceResult();
		
	    if (resultList == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(resultList);
	    }
	}
}
