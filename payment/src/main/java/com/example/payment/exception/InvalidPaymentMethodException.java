package com.example.payment.exception;

public class InvalidPaymentMethodException extends RuntimeException
{
	public InvalidPaymentMethodException(String Message)
	{
		super(Message);	}

}
