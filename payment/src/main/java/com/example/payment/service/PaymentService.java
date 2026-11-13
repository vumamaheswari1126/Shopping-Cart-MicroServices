package com.example.payment.service;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.payment.DTO.OrderResponse;
import com.example.payment.DTO.PaymentRequest;
import com.example.payment.DTO.PaymentResponse;
import com.example.payment.client.OrderClient;
import com.example.payment.entity.Payment;
import com.example.payment.exception.InvalidCustomerID;
import com.example.payment.exception.InvalidPaymentAmount;
import com.example.payment.exception.InvalidPaymentMethodException;
import com.example.payment.repository.PaymentRepository;
@Service
public class PaymentService {

	private PaymentRepository paymentRepository;
	private OrderClient orderClient;


	public PaymentService(PaymentRepository paymentRepository,OrderClient orderClient)
	{
		this.paymentRepository=paymentRepository;
		this.orderClient=orderClient;


	}

	public ResponseEntity<?> payment(PaymentRequest paymentRequest)
	{
		if(paymentRepository.existsByOrderId(paymentRequest.getOrderId()))
		{
			return ResponseEntity.ok("Payment is already exist for the order id");
		}

			Payment payment=new Payment();
			PaymentResponse paymentResponse=new PaymentResponse();


			OrderResponse orderResponse=this.orderClient.getOrderById(paymentRequest.getOrderId());

			if(orderResponse.getTotalAmount()==null ||orderResponse.getTotalAmount().compareTo(BigDecimal.ZERO)<=0)
			{
				throw new InvalidPaymentAmount("Invalid Amount for the Payment");
			}
			payment.setAmount(orderResponse.getTotalAmount());
			if(orderResponse.getCustomerId()<=0)
			{
				throw new InvalidCustomerID("Invalid Customer ID");
			}
			payment.setCustomerId(orderResponse.getCustomerId());
			payment.setOrderId(orderResponse.getOrderId());

//			if (!"PENDING".equalsIgnoreCase(orderResponse.getStatus())) {
//			    throw new RuntimeException("Order is not eligible for payment");
//			}
			payment.setPaymentStatus("SUCESS");
			if(paymentRequest.getPaymentMethod()==null||
					!(paymentRequest.getPaymentMethod().equalsIgnoreCase("UPI")||
			          paymentRequest.getPaymentMethod().equalsIgnoreCase("NETBANKING")||
			          paymentRequest.getPaymentMethod().equalsIgnoreCase("CARD")
					))
			{
				throw new InvalidPaymentMethodException("Invalid Payment Method");
			}
			payment.setPaymentMethod(paymentRequest.getPaymentMethod());

			payment.setPaymentDate(LocalTime.now());
			Payment savedPayment=this.paymentRepository.save(payment);
			paymentResponse.setPaymentId(savedPayment.getPaymentid());
			paymentResponse.setCustomerId(savedPayment.getCustomerId());
			paymentResponse.setOrderId(savedPayment.getOrderId());
			paymentResponse.setTotalAmount(savedPayment.getAmount());
			paymentResponse.setStatus(savedPayment.getPaymentStatus());

			return ResponseEntity.ok(paymentResponse);
		}
		//OrderResponse paymentResponse=this.orderClient.getOrderById(orderId);




	public ResponseEntity<?> viewbyPaymentId(int paymentId) {

		return ResponseEntity.ok(this.paymentRepository.findById(paymentId));

	}
	public ResponseEntity<?> viewbyCustomerId(int customerId)
	{
		return ResponseEntity.ok(this.paymentRepository.findByCustomerId(customerId));
	}

	public ResponseEntity<?> viewallpayment() {

		return ResponseEntity.ok(this.paymentRepository.findAll());
	}

	public ResponseEntity<?> deletepayment(int paymentId) {

	this.paymentRepository.deleteById(paymentId);
	return ResponseEntity.ok("Payment is Deleted Sucessfully");
	}

}
