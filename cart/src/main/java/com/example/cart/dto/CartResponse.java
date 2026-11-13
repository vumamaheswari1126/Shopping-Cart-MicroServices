package com.example.cart.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartResponse {

	private Long cartid;

	private Long customerid;

	private Long productid;

	private String productname;

	private BigDecimal price;

	private Integer quantity;

	private BigDecimal subtotal;

}
