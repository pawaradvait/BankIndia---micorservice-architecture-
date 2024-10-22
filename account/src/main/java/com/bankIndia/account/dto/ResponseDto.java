package com.bankIndia.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "ResponseDto",
    description = "Response DTO for REST API"
)
public class ResponseDto {
 
    private String status;
    private String message;
 
}
