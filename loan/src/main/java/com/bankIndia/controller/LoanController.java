package com.bankIndia.controller;

import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankIndia.constant.LoanConstant;
import com.bankIndia.dto.ResponseDto;
import com.bankIndia.service.LoanService;



@RestController
@RequestMapping(value="/api/loan" , produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoanController {
  
	private final LoanService loanService;
	
 public LoanController(LoanService loanService) {
	// TODO Auto-generated constructor stub
	 this.loanService = loanService;
}	
	
	@GetMapping("/sayHello")
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.status(HttpStatus.OK).body("hello form loan");
	}
	
	
	@PostMapping("/createLoan")
	public ResponseEntity<ResponseDto> applyLoan(@RequestParam(value = "mobileNumber") String mobilenumber){
		
		loanService.createLoan(mobilenumber);
		ResponseDto responseDto = new ResponseDto(LoanConstant.STATUS_201, LoanConstant.MESSAGE_201);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}
	 
}
