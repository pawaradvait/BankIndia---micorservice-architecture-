package com.bankIndia.account.service;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankIndia.account.constant.AccountConstant;
import com.bankIndia.account.dto.AccountDto;
import com.bankIndia.account.dto.CustomerDto;
import com.bankIndia.account.entity.Account;
import com.bankIndia.account.entity.Customers;
import com.bankIndia.account.exception.CustomerAlreadyExistsException;
import com.bankIndia.account.exception.ResourceNotFoundException;
import com.bankIndia.account.mapper.AccountMapper;
import com.bankIndia.account.mapper.CustomerMapper;
import com.bankIndia.account.repo.AccountRepo;
import com.bankIndia.account.repo.CustomerRepo;

@Service
public class AccountServiceImpl implements AccountService {

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


              @Override
              public CustomerDto fetchAccount(String mobileNumber) {

                Optional<Customers> customers = customerRepo.findByMobileNumber(mobileNumber);
    if (customers.isPresent()) {
       
      CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customers.get(), new CustomerDto());
               Account account =    accountRepo.findByCustomerId(customers.get().getCustomerId());
          AccountDto accountDto = AccountMapper.mapToAccountsDto(account, new AccountDto());
    customerDto.setAccountsDto(accountDto);
                return customerDto;
    }else{
      throw new ResourceNotFoundException("Account","mobileNumber",mobileNumber);
    }

              }


              @Override
              public boolean updateAccount(CustomerDto customerDto) {
                // TODO Auto-generated method 
                boolean isUpdated = false;
                AccountDto accountDto = customerDto.getAccountsDto();
                if(accountDto != null){
                    Account account =  accountRepo.findById(accountDto.getAccountNumber())
                    .orElseThrow(()-> new ResourceNotFoundException("Account",
                    "AccountNumber",accountDto.getAccountNumber().toString()));
                  
                    AccountMapper.mapToAccounts(accountDto, account);
                    accountRepo.save(account);
                      
                    Customers customer = customerRepo.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(()-> new ResourceNotFoundException("Customer", "mobileNumber", customerDto.getMobileNumber()));
                  CustomerMapper.mapToCustomer(customerDto, customer);
                  customerRepo.save(customer);

                
                    isUpdated = true;
               
                  }
                  return isUpdated;



              }


              @Override
              public boolean deleteAccount(String mobileNumber) {
                // TODO Auto-generated method stub
  boolean isDeleted = false;
                 Optional<Customers> customers = customerRepo.findByMobileNumber(mobileNumber);
        if(customers.isPresent()){
          accountRepo.deleteByCustomerId(customers.get().getCustomerId());
          customerRepo.deleteById(customers.get().getCustomerId());
        isDeleted = true;
        }else{
          throw new ResourceNotFoundException("Customer","mobileNumber",mobileNumber);

        }
        return isDeleted;

              }

}
