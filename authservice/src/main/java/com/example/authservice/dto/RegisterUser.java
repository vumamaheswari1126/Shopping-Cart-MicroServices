package com.example.authservice.dto;

import org.jspecify.annotations.Nullable;

import lombok.Data;

@Data
public class RegisterUser {

	private int customerId;
	private String password;

}
