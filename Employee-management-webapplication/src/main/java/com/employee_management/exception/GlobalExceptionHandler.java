package com.employee_management.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Specific exception handling
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){
			ErrorDetails errorDetails= new ErrorDetails(new Date(), 
					resourceNotFoundException.getMessage(), webRequest.getDescription(false));
			return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		// Global exception handling
		@ExceptionHandler(Exception.class)
		public ResponseEntity<ErrorDetails> handleGlobalException(Exception e, WebRequest webRequest){
			ErrorDetails errorDetails= new ErrorDetails(new Date(), 
					e.getMessage(), webRequest.getDescription(false));
		    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);	
		} 

}
