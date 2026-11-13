package com.example.cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cart.entity.Cart;
import com.example.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController
{
private CartService cartservice;

public CartController(CartService cartservice)
{
	this.cartservice=cartservice;
}
//Add item
 @PostMapping("/AddCart")
 public Cart addCart(@RequestBody Cart cart)
 {
	return this.cartservice.addCart(cart);

 }

 @GetMapping("/viewall")
 public List<Cart> viewall()
 {
	return this.cartservice.viewallcartproduct();

 }
 //View Cart
 @GetMapping("/view/{customerId}")
 public ResponseEntity<?> viewCart(@PathVariable Long customerId)
 {
	return ResponseEntity.ok( this.cartservice.getCartitemsbyCustomerId(customerId));


 }

 //update quantity
 @PutMapping("/update/{id}")
 public ResponseEntity<?> updateQuantity(@PathVariable Long id,@RequestParam Integer Quantity)
 {
	return ResponseEntity.ok( this.cartservice.updateQuantity(id, Quantity));
 }

 //Remove one item
 @DeleteMapping("/delete/{id}")
 public void deleteItem(@PathVariable Long id)
 {
	 this.cartservice.removeitem(id);
 }
 @DeleteMapping("/deletecustcart/{customerId}")
 public void deleteCustCart(@PathVariable Long customerId)
 {
	 this.cartservice.clearcart(customerId);
 }

 @GetMapping("/viewcartdetails/{customerId}")
 public ResponseEntity<?> viewCartDetails(@PathVariable Long customerId )
 {
	;
	 return ResponseEntity.ok( this.cartservice.getCartResponseByCustomer(customerId));
 }

}
