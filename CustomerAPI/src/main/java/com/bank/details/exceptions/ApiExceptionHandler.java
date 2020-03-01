package com.bank.details.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = ApiException.class)
	public ResponseEntity<Object> exception(ApiException exception) {
		return new ResponseEntity<>("Data not found, please add a customer", HttpStatus.NOT_FOUND);
	}

}
