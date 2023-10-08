package com.shyam.cartsservice.service;

import java.util.List;

import com.shyam.cartsservice.exception.CartException;
import com.shyam.cartsservice.exception.CustomerException;
import com.shyam.cartsservice.exception.ProductException;
import com.shyam.cartsservice.model.Cart;
import com.shyam.cartsservice.model.Product;

public interface CartService {

	public Cart addProductToCart(String customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart removeProductFromCart(String customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart removeAllProduct(String customerId) throws CartException, CustomerException;

	public Cart increaseProductQuantity(String customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart decreaseProductQuantity(String customerId, Integer productId)
			throws CartException, CustomerException, ProductException;
	public String getCustomerId(String email);
	public List<Product> getProductList(String cId);
}
