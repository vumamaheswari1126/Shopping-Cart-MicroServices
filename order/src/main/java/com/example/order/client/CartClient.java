package com.example.order.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import com.example.order.dto.CartResponse;
import com.example.order.exception.CartServiceUnavailableException;

@Service
public class CartClient {

	private RestClient restClient;

	public CartClient(RestClient restClient)
	{
		this.restClient=restClient;
	}

	public List<CartResponse> getCartByCustomerId(Long customerId)
	{
		try
		{
			return restClient.get()
			          .uri("cart/viewcartdetails/{customerId}", customerId)
			          .retrieve()
			          .body(new org.springframework.core.ParameterizedTypeReference<List<CartResponse>>() {});

		}
		catch(ResourceAccessException ex)
		{
			throw new CartServiceUnavailableException("Cart Service is Unavailbale");
		}

}
}
