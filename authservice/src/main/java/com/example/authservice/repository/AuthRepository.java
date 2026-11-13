package com.example.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authservice.dto.LoginResponse;
import com.example.authservice.entity.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity,Integer>{


	UserEntity findByCustomerId(int customeId);

	LoginResponse save(LoginResponse user);
}
