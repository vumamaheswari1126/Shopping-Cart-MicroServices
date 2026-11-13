package com.example.payment.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentResponse {

	private int orderId;
	private int paymentId;
	private int customerId;
	private BigDecimal totalAmount;
	private String status;


}
