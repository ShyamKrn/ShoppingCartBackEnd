package com.shyam.cartsservice.service;

import java.util.List;

import com.shyam.cartsservice.exception.CartException;
import com.shyam.cartsservice.exception.CustomerException;
import com.shyam.cartsservice.exception.OrderException;
import com.shyam.cartsservice.model.Orders;

public interface OrderService {

	public Orders addOrder(String cid) throws OrderException, CustomerException, CartException;

	public Orders updateOrder(Orders order) throws OrderException;

	public Orders viewOrder(Integer orderId) throws OrderException;

	public List<Orders> viewAllOrder() throws OrderException;

	public List<Orders> viewAllOrdersByUserId(String cId) throws OrderException;

}
