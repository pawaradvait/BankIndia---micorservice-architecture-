package com.bankIndia.account.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    
    private  String apiPath;

    private HttpStatus errorCode;

  
    private  String errorMessage;

   
    private LocalDateTime errorTime;
}
