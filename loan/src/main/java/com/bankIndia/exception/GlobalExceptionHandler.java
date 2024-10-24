package com.bankIndia.exception;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bankIndia.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {






@Override
@Nullable 
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	// TODO Auto-generated method stub

	Map<String, String> validationErrors = new HashMap<>();
	List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

	validationErrorList.forEach((error) -> {
		String fieldName = ((FieldError) error).getField();
		String validationMsg = error.getDefaultMessage();
		validationErrors.put(fieldName, validationMsg);
	});
	return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);



}
	
	
	@ExceptionHandler(LoanAlreadyExistExcpetion.class)
    public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExistsException(LoanAlreadyExistExcpetion exception,
            WebRequest webRequest){
ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
webRequest.getDescription(false),
HttpStatus.BAD_REQUEST,
exception.getMessage(),
LocalDateTime.now()
);
return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> resourcenotfoundexception(ResourceNotFoundException ex , WebRequest webRequest){
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				HttpStatus.NOT_FOUND,
				ex.getMessage(),
				LocalDateTime.now()
				);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
	}
	

	
}
