package com.bankIndia.account.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bankIndia.account.dto.LoanDto;
@Component
public class Fallback implements LoanFeignClient{

    @Override
    public ResponseEntity<LoanDto> fetchLoan(String correlationId, String mobilenumber) {
        // TODO Auto-generated method stub
        
    return null;
    }

}
