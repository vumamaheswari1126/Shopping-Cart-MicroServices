package com.example.order.service;

import java.math.BigDecimal;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.order.client.CartClient;
import com.example.order.dto.CartResponse;
import com.example.order.entity.Order;
import com.example.order.exception.EmptyCartException;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private CartClient cartClient;


	public OrderService(OrderRepository orderRepository,CartClient cartClient)
	{
		this.orderRepository=orderRepository;
		this.cartClient=cartClient;
	}

	public Order placeOrder(Order order)
	{
		 List<CartResponse> cartItems =cartClient.getCartByCustomerId(order.getCustomerId());

		 if (cartItems == null || cartItems.isEmpty()) {
		        throw new EmptyCartException("Cart is empty for this customer");
		    }

		 BigDecimal totalamount = cartItems.stream()
		                                   .map(CartResponse::getSubtotal)
		                                   .reduce(BigDecimal.ZERO, BigDecimal::add);

		    order.setTotalAmount(totalamount);
		    order.setStatus("PLACED");

		   // return orderrepository.save(order);

		return this.orderRepository.save(order);
	}

	public List<Order> viewOrder()
	{

		return this.orderRepository.findAll();
	}

	public Order viewOrderByID(Long orderId) {

		return this.orderRepository.findById(orderId)
				      .orElseThrow(()
				      -> new OrderNotFoundException("Enter the valid ID"));
	}
	public List<Order> viewByCustomerID(Long customerId)
	{
		return orderRepository.findByCustomerId(customerId);
	}

}
