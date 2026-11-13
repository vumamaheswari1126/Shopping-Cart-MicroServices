package com.example.authservice.dto;

import lombok.Data;

@Data
public class LoginResponse
{
	private int customerId;
	private String token;

}
