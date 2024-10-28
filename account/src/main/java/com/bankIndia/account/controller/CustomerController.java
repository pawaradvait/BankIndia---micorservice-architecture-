package com.bankIndia.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankIndia.account.dto.CustomerDetailsDto;
import com.bankIndia.account.service.CustomerDetailsService;


@RestController
@Validated
@RequestMapping(value =  "/api"  , produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {


    @Autowired
    private CustomerDetailsService customerDetailsService;


    @GetMapping("/customerDetail")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetail(@RequestParam(value = "mobileNumber") String mobileNumber) {
        return 
        ResponseEntity.status(200).body(
            
        customerDetailsService.getCustomerDetails(mobileNumber)
        );
    } 

}
