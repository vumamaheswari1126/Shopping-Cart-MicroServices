package com.example.authservice.dto;

import com.example.authservice.entity.Role;

import lombok.Data;

@Data
public class AuthenticatedUserResponse
{

     private int customerId;
	 private String role;


}
