package com.example.order.exception;

public class EmptyCartException extends RuntimeException{

	public EmptyCartException(String message)
	{
		super(message);
	}

}
