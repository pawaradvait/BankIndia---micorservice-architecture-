package com.bankIndia.account.service;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankIndia.account.constant.AccountConstant;
import com.bankIndia.account.dto.CustomerDto;
import com.bankIndia.account.entity.Account;
import com.bankIndia.account.entity.Customers;
import com.bankIndia.account.exception.CustomerAlreadyExistsException;
import com.bankIndia.account.mapper.CustomerMapper;
import com.bankIndia.account.repo.AccountRepo;
import com.bankIndia.account.repo.CustomerRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public boolean createAccount(CustomerDto customerDto) {
  
        String mobileNumber = customerDto.getMobileNumber();
       
        Optional<Customers> customers =  customerRepo.findByMobileNumber(mobileNumber);
      if(customers.isPresent()){
        throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber" + mobileNumber);
      }
        
          Customers customers2 = CustomerMapper.mapToCustomer(customerDto, new Customers());
        customers2.setCreatedAt(LocalDateTime.now());
        customers2.setCreatedBy("Dev");
          Customers customers3 =  customerRepo.save(customers2);
         
        accountRepo.save(createNewAccount(customers3));

        return true;



    }

    
              private Account createNewAccount(Customers customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstant.SAVINGS);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Dev");
        return newAccount;
    }

}
