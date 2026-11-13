package com.example.apigateway.security;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.apigateway.dto.AuthenticatedUserResponse;

import reactor.core.publisher.Mono;
@Component
public class CustomReactiveAuthenticationManager implements ReactiveAuthenticationManager {


	 private final WebClient webClient;

	    public CustomReactiveAuthenticationManager(WebClient webClient)
	    {
	        this.webClient = webClient;
	    }

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String customerId = authentication.getName();
        String password = authentication.getCredentials().toString();

        return webClient.post()
                .uri("/auth/Login")
                .bodyValue(new LoginRequest(Integer.parseInt(customerId), password))
                .retrieve()
                .bodyToMono(AuthenticatedUserResponse.class)
                .map(user -> {

                    Authentication authenticated =
                            new UsernamePasswordAuthenticationToken(
                                    String.valueOf(user.getCustomerId()),
                                    null,
                                    List.of(
                                            new SimpleGrantedAuthority(
                                                    "ROLE_" + user.getRole())
                                    )
                            );

                    return authenticated;
                })
                .onErrorMap(error ->
                        new BadCredentialsException(
	                            "Invalid Customer ID or Password"));

	}

    private record LoginRequest(int customerId, String password) {}

	}
