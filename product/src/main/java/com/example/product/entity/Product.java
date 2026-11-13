package com.example.product.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @NotBlank(message="Product name is required")
	private String name;

	private String description;

	@NotNull(message="Product price is required")
	@Positive(message="product price must be greater than zero")
	private BigDecimal price;

	@NotNull(message="Stock number is required")
	@Min(value=0)
	private Integer stock;

	@NotNull(message="Product Category is required")
	private String category;
}
