package com.shyam.cartsservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.cartsservice.exception.CartException;
import com.shyam.cartsservice.exception.CustomerException;
import com.shyam.cartsservice.exception.OrderException;
import com.shyam.cartsservice.model.Cart;
import com.shyam.cartsservice.model.Customer;
import com.shyam.cartsservice.model.Orders;
import com.shyam.cartsservice.model.Product;
import com.shyam.cartsservice.repository.CartRepo;
import com.shyam.cartsservice.repository.CustomerRepo;
import com.shyam.cartsservice.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo oRepo;

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CartRepo cartRepo;

	@Override
	public Orders addOrder(String cid) throws OrderException, CustomerException, CartException {

		Optional<Customer> opt = customerRepo.findById(cid);
		if (opt.isEmpty()) {
			throw new CustomerException("Customer not found");
		}

		Customer c = opt.get();
		Cart cart = c.getCart();
		int cartId=cart.getCartId();//1
		Orders o = new Orders();
		o.setOrderId(cartId);
		o.setDate(LocalDateTime.now());
		o.setOrderStatus("Pending");
		o.setAddress(c.getAddress());
		o.setCustomer(c);
//		if (cart.getProducts().isEmpty()) {
//			throw new CartException("add minimum one product to order!");
//		} 
		if (cartRepo.findById(cartId).get().getProducts().isEmpty()) {
			throw new CartException("add minimum one product to order!");
		} 
		else {
			o.setProductList(new ArrayList<>(cartRepo.findById(cartId).get().getProducts()));
			double totalprice = 0;
			for(Product productObj : cartRepo.findById(cartId).get().getProducts()) {
				totalprice += productObj.getPrice();
			}
			o.setTotalPrice(totalprice);
			return oRepo.save(o);
		}

	}

	@Override
	public Orders updateOrder(Orders order) throws OrderException {
		Orders o = oRepo.findById(order.getOrderId()).orElseThrow(() -> new OrderException("Order not found"));
		if (o != null) {
			oRepo.save(order);
		}
		return o;
	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		Optional<Orders> o = oRepo.findById(orderId);
		if (o.isPresent()) {
			return o.get();
		} else {
			throw new OrderException("order not present!!");
		}

	}

	@Override
	public List<Orders> viewAllOrder() throws OrderException {
		List<Orders> orders = oRepo.findAll();
		if (orders.size() > 0) {
			return orders;
		} else {
			throw new OrderException("Order not found");
		}
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(String uderId) throws OrderException {
		List<Orders> orders = customerRepo.findAllOrderByCId(uderId);
		if (orders.size() > 0) {
			return orders;
		} else {
			throw new OrderException("Order not found");
		}
	}
}
