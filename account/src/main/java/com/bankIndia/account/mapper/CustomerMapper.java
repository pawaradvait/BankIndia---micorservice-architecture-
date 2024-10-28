package com.bankIndia.account.mapper;

import com.bankIndia.account.dto.CustomerDetailsDto;
import com.bankIndia.account.dto.CustomerDto;
import com.bankIndia.account.entity.Customers;

public class CustomerMapper {
  public static CustomerDto mapToCustomerDto(Customers customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customers mapToCustomer(CustomerDto customerDto, Customers customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

        public static CustomerDetailsDto mapToCustomerDetailsDto(Customers customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDto;
    }
}
