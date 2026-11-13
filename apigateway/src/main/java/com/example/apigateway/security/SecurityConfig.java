package com.example.apigateway.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig
{


	    @Bean
	    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,CustomReactiveAuthenticationManager customAuthenticationManager)
	    {

	        return http
	        		 .authenticationManager(customAuthenticationManager)
	                .csrf(csrf -> csrf.disable())
	                .authorizeExchange(exchange -> exchange
	                        .pathMatchers("/auth/**").permitAll()
	                        .pathMatchers(
	                                "/product/AddProduct",
	                                "/product/UpdateProduct/**",
	                                "/product/DeleteProduct/**"
	                        ).hasRole("ADMIN")
	                        .pathMatchers("/product/**").hasAnyRole("USER", "ADMIN")
	                        .pathMatchers("/cart/**", "/order/**", "/payment/**")
	                        .hasAnyRole("USER", "ADMIN")

	                        .anyExchange().authenticated()
	                )
	                .formLogin(Customizer.withDefaults())
	                .build();
	    }
	}
