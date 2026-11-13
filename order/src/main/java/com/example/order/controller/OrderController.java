package com.example.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService)
	{
		this.orderService=orderService;
	}

	@PostMapping("/addorder")
	public Order placeOrder(@RequestBody Order order)
	{
		return orderService.placeOrder(order);
	}


	@GetMapping("/vieworder")
	public List<Order> viewOrder()
	{
		return this.orderService.viewOrder();
	}

	@GetMapping("/vieworder/{orderId}")
	public Order viewOrderByID(@PathVariable Long orderId)
	{
		return this.orderService.viewOrderByID(orderId);
	}
	@GetMapping("/viewbycustomerid/{customerId}")
	public List<Order> viewByCustomerID(@PathVariable Long customerId)
	{
		return this.orderService.viewByCustomerID(customerId);
	}


}
