package com.example.authservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.authservice.dto.RegisterUser;
import com.example.authservice.entity.UserEntity;
import com.example.authservice.repository.AuthRepository;
import com.example.authservice.service.AuthService;
@Component
public class AdminInitializer implements CommandLineRunner{
	private final AuthService authService;
	private final AuthRepository authRepository;

	public AdminInitializer(AuthService authService,AuthRepository authRepository) {
	    this.authService = authService;
	    this.authRepository = authRepository;
	}
	@Override
	public void run(String... args) throws Exception {

		 int adminId = 1001;

		    UserEntity existingAdmin =
		            authRepository.findByCustomerId(adminId);

		    if (existingAdmin == null) {

		        RegisterUser admin = new RegisterUser();
		        admin.setCustomerId(adminId);
		        String adminPassword = System.getenv("ADMIN_PASSWORD");

		        if (adminPassword == null || adminPassword.isBlank()) {
		            throw new IllegalStateException("ADMIN_PASSWORD environment variable is missing");
		        }

		        admin.setPassword(adminPassword);
		        authService.createAdmin(admin);
		        System.out.println("Default ADMIN account created!");
	}}}
