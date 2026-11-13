package com.example.order.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmptyCartException.class)
	public ResponseEntity<?> handleEmptyCart(EmptyCartException ex)
	{
		Map<Object,Object>Response =new HashMap<Object,Object>();
		Response.put("Message",ex.getMessage());
		Response.put("Status",400);
		Response.put("error", "BadRequest");
		return ResponseEntity.ok(Response);
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<?> handleOrderNotFound(OrderNotFoundException ex)
	{
		Map<Object,Object> Response=new HashMap<Object,Object>();
		Response.put("Message", ex.getMessage());
		Response.put("Status",400);
		Response.put("error", "Bad Request");
		return ResponseEntity.ok(Response);

	}

	@ExceptionHandler(CartServiceUnavailableException.class)
	public ResponseEntity<?> handleCartServiceUnavailable(CartServiceUnavailableException ex)
	{
		Map<Object,Object> Response=new HashMap<Object,Object>();
		Response.put("Message",ex.getMessage());
		Response.put("Status",HttpStatus.INTERNAL_SERVER_ERROR);
		Response.put("Error ", "Cart Service is Not Reachable");
		return ResponseEntity.ok(Response);

	}

}
