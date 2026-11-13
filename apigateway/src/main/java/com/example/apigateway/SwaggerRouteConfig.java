package com.example.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerRouteConfig {

	 @Bean
	    public RouteLocator swaggerRoutes(RouteLocatorBuilder builder) {

	        return builder.routes()

	                .route("product-swagger", r -> r
	                        .path("/swagger-docs/product")
	                        .filters(f -> f.setPath("/v3/api-docs"))
	                        .uri("http://localhost:8081"))

	                .route("cart-swagger", r -> r
	                        .path("/swagger-docs/cart")
	                        .filters(f -> f.setPath("/v3/api-docs"))
	                        .uri("http://localhost:8082"))

	                .route("order-swagger", r -> r
	                        .path("/swagger-docs/order")
	                        .filters(f -> f.setPath("/v3/api-docs"))
	                        .uri("http://localhost:8083"))

	                .route("payment-swagger", r -> r
	                        .path("/swagger-docs/payment")
	                        .filters(f -> f.setPath("/v3/api-docs"))
	                        .uri("http://localhost:8084"))
	                .route("auth-swagger", r -> r
	                        .path("/swagger-docs/auth")
	                        .filters(f -> f.setPath("/v3/api-docs"))
	                        .uri("http://localhost:8085"))

	                .build();

	    }
	}
