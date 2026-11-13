package com.example.order.exception;

public class CartServiceUnavailableException extends RuntimeException {
    public CartServiceUnavailableException(String message)
    {
    	super(message);
    }
}
