package com.bankIndia.account.service;

import com.bankIndia.account.dto.CustomerDetailsDto;

public interface CustomerDetailsService {

    CustomerDetailsDto getCustomerDetails(String mobileNumber);
}
