package com.example.payment.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderResponse {

	private int orderId;
	private int customerId;
	private BigDecimal totalAmount;
	private String status;
}
