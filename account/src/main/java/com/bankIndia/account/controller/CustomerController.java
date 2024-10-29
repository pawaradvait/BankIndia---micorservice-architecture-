package com.bankIndia.account.controller;

import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankIndia.account.dto.CustomerDetailsDto;
import com.bankIndia.account.service.CustomerDetailsService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Validated
@RequestMapping(value =  "/api"  , produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class CustomerController {



    @Autowired
    private CustomerDetailsService customerDetailsService;


    @GetMapping("/customerDetail")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetail(
    @RequestHeader(value="BANKINDIA-correlation-id") String correlationId,    
    @RequestParam(value = "mobileNumber") String mobileNumber) {

 log.debug("BANKINDIA-correlation-id : {}",correlationId);
System.out.println("correlation id : "+correlationId);
        return 
        ResponseEntity.status(200).body(
            
        customerDetailsService.getCustomerDetails(mobileNumber , correlationId)
        );
    } 

}
