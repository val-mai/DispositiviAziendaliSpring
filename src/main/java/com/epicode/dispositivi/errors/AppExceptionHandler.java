package com.epicode.dispositivi.errors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityExistsException.class)
	protected ResponseEntity<String> handleEntityExistsException(EntityExistsException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
		return new ResponseEntity<String>("Username or password are incorrect, try again", HttpStatus.BAD_REQUEST);
	}
}
