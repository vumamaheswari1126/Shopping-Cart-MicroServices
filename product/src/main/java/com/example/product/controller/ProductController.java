package com.example.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService productservice;

	public ProductController(ProductService productservice)
	{
		this.productservice=productservice;
	}

	@PostMapping("/AddProduct")
	public ResponseEntity<?> addProduct(@Valid @RequestBody Product product)
	{
		this.productservice.addProduct(product);
		return new  ResponseEntity<>(product,HttpStatus.CREATED);
	}

	@GetMapping("GetProduct")
	public List<Product> getProduct()
	{
	   return this.productservice.getProduct();


	}
	@GetMapping("/GetIDProduct/{id}")
	public ResponseEntity<?> getByIDProduct(@PathVariable Long id)
	{
		return ResponseEntity.ok(this.productservice.getProductByID(id));
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody Product product)
	{
		return ResponseEntity.ok(this.productservice.updateProduct(id, product));
	}

	@DeleteMapping("/DeleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id)
	{
		this.productservice.deleteProduct(id);
		return ResponseEntity.ok("Product deleted Successfully");

	}
}
