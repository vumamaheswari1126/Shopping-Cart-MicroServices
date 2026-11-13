
package com.example.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {

	List<Cart> findByCustomerId(Long customerId);
}
