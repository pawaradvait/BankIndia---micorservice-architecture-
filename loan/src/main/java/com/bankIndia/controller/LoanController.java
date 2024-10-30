package com.bankIndia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankIndia.constant.LoanConstant;
import com.bankIndia.dto.ErrorResponseDto;
import com.bankIndia.dto.LoanDto;
import com.bankIndia.dto.LoanProperties;
import com.bankIndia.dto.ResponseDto;
import com.bankIndia.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Tag(
        name = "CRUD REST APIs for Loans in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@RequestMapping(value="/api/loan" , produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
public class LoanController {
  
        @Autowired
	private  LoanService loanService;
	
        @Autowired
        private LoanProperties loanProperties;

        
	

	    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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
	
	@PostMapping("/createLoan")
	public ResponseEntity<ResponseDto> applyLoan(
		@Valid
		@RequestParam
		@Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
	
		
		loanService.createLoan(mobileNumber);
		ResponseDto responseDto = new ResponseDto(LoanConstant.STATUS_201, LoanConstant.MESSAGE_201);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}
    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
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
	@PutMapping("/updateLoan")
	public ResponseEntity<ResponseDto> updateLoan(@Valid @org.springframework.web.bind.annotation.RequestBody LoanDto loanDto){
		
		loanService.updateLoan(loanDto);
		ResponseDto responseDto = new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}


    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
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
	@DeleteMapping("/deleteLoan")
	public ResponseEntity<ResponseDto> deleteLoan(
		 @RequestParam(value = "mobileNumber")
		 @Valid  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
		String mobilenumber){
		
		loanService.deleteLoan(mobilenumber);
		ResponseDto responseDto = new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@Operation(
		summary = "Fetch Loan Details REST API",
		description = "REST API to fetch loan details based on a mobile number"
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

	@GetMapping("/fetchLoan")
	public ResponseEntity<LoanDto> fetchLoan(
         @RequestHeader(value = "BANKINDIA-correlation-id", required = false) String correlationId,      
        @Valid @RequestParam(value = "mobileNumber") String mobilenumber){
		

                log.debug("BANKINDIA-correlation-id : {}",correlationId);
                log.debug("invoking loan service");

		LoanDto loanDto = loanService.fetchLoan(mobilenumber);
		return ResponseEntity.status(HttpStatus.OK).body(loanDto);
		
	}

        @GetMapping("/contact-info")
        public ResponseEntity<LoanProperties> getContactInfo(){
                log.debug("invoking loan service");

                return ResponseEntity.status(HttpStatus.OK).body(loanProperties);
        }
	
}
