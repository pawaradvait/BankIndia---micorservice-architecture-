package com.bankIndia.account.service;

import com.bankIndia.account.dto.CustomerDto;

public interface AccountService {

    boolean createAccount(CustomerDto customerDto);
     
    CustomerDto fetchAccount(String mobileNumber) ;
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
   

}
