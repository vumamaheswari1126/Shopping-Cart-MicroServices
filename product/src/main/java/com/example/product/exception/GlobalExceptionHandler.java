package com.example.product.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import  com.example.product.exception.ProductNotFoundException;
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex)
	{
		Map<Object,Object> response=new HashMap<Object,Object>();
		response.put("Action","Product Id is Invalid");
		response.put("Message",ex.getMessage());
		response.put("Status", HttpStatus.NOT_FOUND.value());

		return   ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

}
