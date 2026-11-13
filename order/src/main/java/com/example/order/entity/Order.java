package com.example.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="OrderDetails")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderid")
	private long orderId;

	private long customerId;

	private String status;

	@Column(name="totalamount")
	private BigDecimal totalAmount;

	private LocalDateTime date;

	@PrePersist
	public void setDate()
	{
		date=LocalDateTime.now();
	}



}
