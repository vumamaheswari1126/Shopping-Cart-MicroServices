package com.example.cart.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.example.cart.dto.ProductResponse;
@Component
public class ProductClient {

	private RestClient restclient;

	public ProductClient(RestClient restclient)
	{
		this.restclient=restclient;
	}

	public ProductResponse getProductbyID(Long productId)
	{
		return restclient.get()
				         .uri("/App/GetIDProduct/{id}", productId)
				         .retrieve()
				         .body(ProductResponse.class);
	}
}
