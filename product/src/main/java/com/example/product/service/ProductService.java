package com.example.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.entity.Product;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.repository.ProductRepository;

import jakarta.el.MethodNotFoundException;

@Service
public class ProductService
{

	private ProductRepository productrepository;

	public ProductService(ProductRepository productrepository)
	{
		this.productrepository=productrepository;
	}
	//To add the Product
	public Product addProduct(Product product )
	{
		return this.productrepository.save(product);


	}
	//Get All Product
	public List<Product> getProduct()
	{
		return this.productrepository.findAll();
	}
	//Get By Id

	public Product getProductByID(Long id)
	{
		return this.productrepository.findById(id)
				             .orElseThrow(()
				    		-> new ProductNotFoundException ("Product Not Found "+id));

	}
	//update Product
	public Product updateProduct(Long id,Product updatedProduct)
	{
		Product existingProduct = getProductByID(id);
		existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setCategory(updatedProduct.getCategory());

        return productrepository.save(existingProduct);


	}
	//Delete product
	public void deleteProduct(Long id)
	{
		Product product=getProductByID(id);
		this.productrepository.delete(product);
	}

}
