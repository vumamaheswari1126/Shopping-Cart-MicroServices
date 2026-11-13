package com.example.payment.DTO;

import lombok.Data;

@Data
public class PaymentRequest {

	private int orderId;
	private String paymentMethod;
}
