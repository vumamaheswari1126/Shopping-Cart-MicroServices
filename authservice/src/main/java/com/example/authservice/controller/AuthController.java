package com.example.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authservice.dto.LoginUser;
import com.example.authservice.dto.RegisterUser;
import com.example.authservice.entity.UserEntity;
import com.example.authservice.service.AuthService;
@RestController
@RequestMapping("/auth")
public class AuthController
{
	private AuthService authService;
	public  AuthController(AuthService authService)
	{
		this.authService=authService;

	}

	@PostMapping("/Register")
	public ResponseEntity<UserEntity> register(@RequestBody RegisterUser registerUser)
	{
		return ResponseEntity.ok(this.authService.registerUser(registerUser));
	}

	@PostMapping("/Login")
	public ResponseEntity<?> login(@RequestBody LoginUser loginUser)
	{
		return ResponseEntity.ok(this.authService.authenticatedUser(loginUser));
	}
}
