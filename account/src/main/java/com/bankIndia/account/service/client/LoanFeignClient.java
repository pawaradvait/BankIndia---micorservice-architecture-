package com.bankIndia.account.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankIndia.account.dto.LoanDto;

import jakarta.validation.Valid;

@FeignClient("loan")
public interface LoanFeignClient {

    
	@GetMapping(value = "/api/loan/fetchLoan" , consumes = MediaType.APPLICATION_JSON_VALUE, produces ={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<LoanDto> fetchLoan( @RequestParam(value = "mobileNumber") String mobilenumber);

}