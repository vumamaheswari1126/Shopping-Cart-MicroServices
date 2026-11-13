package com.example.cart.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<?> handleInsufficientStock(InsufficientStockException ex)
	{

		Map <String,Object> response=new HashMap<String,Object>();
		response.put("Message", ex.getMessage());
		response.put("Status", HttpStatus.FAILED_DEPENDENCY);
		return ResponseEntity.ok(response);

	}

}
