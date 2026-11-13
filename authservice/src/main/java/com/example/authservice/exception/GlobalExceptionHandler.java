package com.example.authservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<?> handleAuthentication(InvalidCredentialsException ex)
	{
	  Map<Object,Object> response=new HashMap<Object,Object>();
	  response.put("Message",ex.getMessage());
	  response.put("Status",HttpStatus.UNAUTHORIZED);
	  return ResponseEntity.ok(response);

	}

	@ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserExist(UserAlreadyExistsException ex)
    {
		  Map<Object,Object> response=new HashMap<Object,Object>();
		  response.put("Message",ex.getMessage());
		  response.put("Status",HttpStatus.UNAUTHORIZED);
		  return ResponseEntity.ok(response);

    }
}
