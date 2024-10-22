package com.bankIndia.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankIndia.account.constant.AccountConstant;
import com.bankIndia.account.dto.CustomerDto;
import com.bankIndia.account.dto.ResponseDto;
import com.bankIndia.account.service.AccountService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public ResponseEntity<String> getAccounts() {
        return ResponseEntity.ok("Accounts");
    }

    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> postMethodName(@RequestBody CustomerDto customerDto ) {
    
        boolean isSaved = accountService.createAccount(customerDto);
          if(isSaved){
            return ResponseEntity.status(201)
            .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
          }else{
            return ResponseEntity.status(417)
            .body(new ResponseDto(AccountConstant.STATUS_417, AccountConstant.MESSAGE_417_UPDATE));
          }
      

    }
    

}
