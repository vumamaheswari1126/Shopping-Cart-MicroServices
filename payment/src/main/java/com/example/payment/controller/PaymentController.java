package com.example.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.DTO.PaymentRequest;
import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService)
	{
		this.paymentService=paymentService;
	}
	@PostMapping("/pay")
	public ResponseEntity<?> payment(@RequestBody PaymentRequest paymentRequest)
	{
		return this.paymentService.payment(paymentRequest);


	}

	@GetMapping("/viewpaymentbyid/{paymentId}")
	public ResponseEntity<?> viewPaymentById(@PathVariable int paymentId)
	{
		return this.paymentService.viewbyPaymentId(paymentId);
	}
	@GetMapping("/viewcustomerpaymentbyid/{customerid}")
	public ResponseEntity<?> viewPaymentByCustomerId(@PathVariable int customerId)
	{
		return this.paymentService.viewbyCustomerId(customerId);
	}

	@GetMapping("/viewallpay")
	public ResponseEntity<?> viewAllPayment()
	{
		return this.paymentService.viewallpayment();
	}
	@DeleteMapping("/deletepay/{paymentId}")
	public ResponseEntity<?> deletePayment(@PathVariable int paymentId)
	{
		return this.paymentService.deletepayment(paymentId);
	}
}
