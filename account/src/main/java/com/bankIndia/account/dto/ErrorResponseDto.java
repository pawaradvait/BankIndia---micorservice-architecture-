package com.bankIndia.account.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
name = "Error Response",
    
description = "Error Response"

)
public class ErrorResponseDto {

    
    private  String apiPath;

    private HttpStatus errorCode;

  
    private  String errorMessage;

   
    private LocalDateTime errorTime;
}
