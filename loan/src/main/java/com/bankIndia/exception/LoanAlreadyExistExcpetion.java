package com.bankIndia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistExcpetion extends RuntimeException{

	public LoanAlreadyExistExcpetion(String message) {
		super(message);
	}
}
