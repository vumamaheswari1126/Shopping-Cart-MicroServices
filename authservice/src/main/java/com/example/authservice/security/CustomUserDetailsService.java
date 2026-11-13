package com.example.authservice.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authservice.entity.UserEntity;
import com.example.authservice.repository.AuthRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService
{

    private final AuthRepository authRepository;

    public CustomUserDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {


        int customerId = Integer.parseInt(username);
        UserEntity user = authRepository.findByCustomerId(customerId);
        if (user == null) {
            throw new UsernameNotFoundException("Customer not found");
        }


        return User.builder()
                .username(String.valueOf(user.getCustomerId()))
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
