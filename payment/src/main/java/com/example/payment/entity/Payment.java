package com.example.payment.entity;

import java.math.BigDecimal;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "paymentid")
	private int paymentid;
	@Column(name = "customerid")
	private int customerId;
	@Column(name = "orderid")
	private int orderId;
	private BigDecimal amount;
	private String paymentMethod;
	private String paymentStatus;
	private String razorpayPaymentId;
	private String razorpayOrderId;
	private LocalTime paymentDate;

}
