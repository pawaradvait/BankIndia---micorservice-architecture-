package com.bankIndia.account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankIndia.account.dto.AccountDto;
import com.bankIndia.account.dto.CustomerDetailsDto;
import com.bankIndia.account.dto.LoanDto;
import com.bankIndia.account.entity.Account;
import com.bankIndia.account.entity.Customers;
import com.bankIndia.account.exception.CustomerAlreadyExistsException;
import com.bankIndia.account.exception.ResourceNotFoundException;
import com.bankIndia.account.mapper.AccountMapper;
import com.bankIndia.account.mapper.CustomerMapper;
import com.bankIndia.account.repo.AccountRepo;
import com.bankIndia.account.repo.CustomerRepo;
import com.bankIndia.account.service.client.LoanFeignClient;
import com.netflix.discovery.converters.Auto;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService{

    @Autowired
    private AccountRepo accountRepo;

    @Autowired  
    private CustomerRepo customerRepo;

    @Autowired
    private LoanFeignClient loanFeignClient;

    

    @Override
    public CustomerDetailsDto getCustomerDetails(String mobileNumber) {

   Customers customers =  customerRepo.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
   Account account = accountRepo.findByCustomerId(customers.getCustomerId());
   ResponseEntity<LoanDto> loanresponse =  loanFeignClient.fetchLoan(mobileNumber);
     LoanDto loanDto = loanresponse.getBody();
     CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customers, new CustomerDetailsDto());
  customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountsDto(account, new AccountDto()));
 customerDetailsDto.setLoanDto(loanDto);

  return customerDetailsDto;
      

        
    }

}
