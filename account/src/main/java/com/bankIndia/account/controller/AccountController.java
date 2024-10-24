package com.bankIndia.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankIndia.account.constant.AccountConstant;
import com.bankIndia.account.dto.AccountContactInfoDto;
import com.bankIndia.account.dto.CustomerDto;
import com.bankIndia.account.dto.ErrorResponseDto;
import com.bankIndia.account.dto.ResponseDto;
import com.bankIndia.account.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.val;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api")
@Tag(
    name = "curd rest api for account in BankIndia",
    description = "curd rest api for account in BankIndia for create read update and delete the account"
)
public class AccountController {

    @Autowired
    private AccountService accountService;
  
    @Autowired
    private AccountContactInfoDto accountContactInfoDto;

    @Value("${build.version}")        
    private String version;

@Operation(summary = "createAccount")
@ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "created successfully"
    ),
    
    @ApiResponse(
        responseCode = "417",
        description = "account already exists"
    )
}
)
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> postMethodName(@RequestBody CustomerDto customerDto ) {
    
        boolean isSaved = accountService.createAccount(customerDto);
          if(isSaved){
            return ResponseEntity.status(201)
            .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
          }else{
            return ResponseEntity.status(417)
            .body(new ResponseDto(AccountConstant.STATUS_417, AccountConstant.MESSAGE_417_UPDATE));
          }
      

    }
   @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchAccount")
    public ResponseEntity<CustomerDto> getAccount( @RequestParam 
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
String mobileNumber) {
     
    CustomerDto  customerDto=  accountService.fetchAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);

    }    

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto) {
         
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(200)
            .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }else{
            return ResponseEntity.status(417)
            .body(new ResponseDto(AccountConstant.STATUS_417, AccountConstant.MESSAGE_417_UPDATE));
        }
    }
     
    @Operation(
        summary = "Delete Account & Customer Details REST API",
        description = "REST API to delete Customer &  Account details based on a mobile number"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "417",
                description = "Expectation Failed"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
}
)

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam 
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")

    String mobileNumber) {
         
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(200)
            .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }else{
            return ResponseEntity.status(417)
            .body(new ResponseDto(AccountConstant.STATUS_417, AccountConstant.MESSAGE_417_DELETE));
        }
    }


    @GetMapping("/build-info")
    public ResponseEntity<String> getVersion(){
    
        return ResponseEntity.status(200).body(version);
   
}

@GetMapping("/contact-info")
public ResponseEntity<AccountContactInfoDto> getContactInfo(){
    
    return ResponseEntity.status(200).body(accountContactInfoDto);
   
}

}