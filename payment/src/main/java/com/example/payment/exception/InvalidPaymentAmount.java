package com.example.payment.exception;

public class InvalidPaymentAmount extends RuntimeException{

	public InvalidPaymentAmount(String Message)
	{
		super(Message);
	}
}
