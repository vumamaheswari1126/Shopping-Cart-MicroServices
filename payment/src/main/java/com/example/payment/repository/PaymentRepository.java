package com.example.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.DTO.OrderResponse;
import com.example.payment.DTO.PaymentResponse;
import com.example.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>
{

	PaymentResponse save(OrderResponse orderResponse);
    List<Payment> findByCustomerId(int customerid);
    Boolean existsByOrderId(int orderid);


}
