package com.example.payment.client;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.payment.DTO.OrderResponse;

@Service
public class OrderClient {

	private RestClient restClient;
	public OrderClient(RestClient restClient)
	{
		this.restClient=restClient;
	}

	public  OrderResponse getOrderById(int orderId)
	{
		return restClient.get()
				         .uri("/order/vieworder/{orderId}",orderId)
				         .retrieve()
				         .body(OrderResponse.class);
	}
}
