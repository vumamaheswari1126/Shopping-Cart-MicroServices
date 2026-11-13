package com.example.payment.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidCustomerID.class)
	public ResponseEntity<?> handlecustId(InvalidCustomerID ex)
	{
		Map<Object,Object> Response=new HashMap<Object,Object>();
		Response.put("Message", ex.getMessage());
		Response.put("Status",HttpStatus.CONFLICT);
		return ResponseEntity.ok(Response);
	}
	@ExceptionHandler(InvalidPaymentAmount.class)
	public ResponseEntity<?> handlePaymentAmount(InvalidPaymentAmount ex)
	{
		Map<Object,Object> Response=new HashMap<Object,Object>();
		Response.put("Message", ex.getMessage());
		Response.put("Status",HttpStatus.NOT_ACCEPTABLE);
		return ResponseEntity.ok(Response);
	}
	@ExceptionHandler(InvalidPaymentMethodException.class)
	public ResponseEntity<?> handlePaymentMethod(InvalidPaymentMethodException ex)
	{
		Map<Object,Object> Response=new HashMap<Object,Object>();
		Response.put("MessageS", ex.getMessage());
		Response.put("Status",HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(Response);

	}

}
