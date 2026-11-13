package com.example.payment.exception;

public class InvalidCustomerID extends RuntimeException{
	public InvalidCustomerID(String message)
	{
		super(message);
	}

}
