package com.example.cart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.cart.dto.CartResponse;
import com.example.cart.dto.ProductResponse;
import com.example.cart.entity.Cart;
import com.example.cart.exception.InsufficientStockException;
import com.example.cart.repository.CartRepository;

@Service
public class CartService
{
	public CartRepository cartrepository;
	public ProductClient productclient;
	ProductResponse productresponse;
	public CartService(CartRepository cartrepository,ProductClient productclient)
	{
		this.cartrepository=cartrepository;
		this.productclient=productclient;

	}
	//To add cart items
	public Cart addCart(Cart cart)
	{      // Validate quantity

		  if (cart.getQuantity() == null ||cart.getQuantity() <= 0) {
          throw new InsufficientStockException( "Quantity must be greater than zero");
      }


		    // Get product details from Product Service
		    ProductResponse product =productclient.getProductbyID(cart.getProductId());
		    System.out.println("Product details received: " + product);
	        System.out.println("Available stock: " + product.getStock());
	        System.out.println("Requested quantity: " + cart.getQuantity());


		  if (product.getStock() == null ||product.getStock() < cart.getQuantity()) {
	            throw new InsufficientStockException( "Insufficient stock for product ID: "+ cart.getProductId());
	        }

		return this.cartrepository.save(cart);



	}
	//get cart items By customer ID
	public List<Cart> getCartitemsbyCustomerId(Long customerId)
	{
		return this.cartrepository.findByCustomerId(customerId);

	}

	//update quantity
	public Cart updateQuantity(Long id,Integer quantity)
	{


		Cart cart = cartrepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Cart item not found: " + id));
        if (quantity == null || quantity <= 0) {
            throw new InsufficientStockException( "Quantity must be greater than zero");
        }

        ProductResponse product =productclient.getProductbyID(cart.getProductId());

        if (product.getStock() == null ||product.getStock() < quantity) {
            throw new InsufficientStockException( "Insufficient stock for product ID: "+ cart.getProductId());
        }
//
//		Cart  cart=cartrepository.findById(id)
//				.orElseThrow(()->new RuntimeException("Cart item is not Found "+id));
		cart.setQuantity(quantity);
		return cartrepository.save(cart);


	}
	//remove the item
	public String removeitem(Long id)
	{
		Cart cart=this.cartrepository.findById(id)
				   .orElseThrow(()->new RuntimeException("Cart id is not found " +id));
		this.cartrepository.delete(cart);
		return "Cart Item is deleted sucessfully";

	}
	public String clearcart(Long customerId)
	{
		List<Cart> items=this.cartrepository.findByCustomerId(customerId);
		cartrepository.deleteAll(items);
		return "Cart item of customer id "+customerId+" deleted sucessfully";

	}
	public List<Cart> viewallcartproduct() {
		List <Cart> response=this.cartrepository.findAll();
		return response;
	}
	public List<CartResponse> getCartResponseByCustomer(Long customerId)
	{
		List<Cart> cartItems=cartrepository.findByCustomerId(customerId);
		List<CartResponse> responses=new ArrayList<>();



		for(Cart cart:cartItems)
		{
			ProductResponse productresponse=productclient.getProductbyID(cart.getProductId());

			CartResponse cartresponse =new CartResponse();

			    cartresponse.setCartid(cart.getCartid());
		        cartresponse.setCustomerid(cart.getCustomerId());
		        cartresponse.setProductid(cart.getProductId());
		        cartresponse.setProductname(productresponse.getName());
		        cartresponse.setPrice(productresponse.getPrice());
		        cartresponse.setQuantity(cart.getQuantity());

		        BigDecimal subtotal = productresponse.getPrice()
		                .multiply(BigDecimal.valueOf(cart.getQuantity()));

		        cartresponse.setSubtotal(subtotal);

		        responses.add(cartresponse);
		    }

		    return responses;
		}





	}
