package com.example.order.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class CartResponse {

	private Long cartid;

	private Long customerId;

	private Long productId;

	private String productName;

	private BigDecimal price;

	private Integer quantity;

	private BigDecimal subtotal;

}
