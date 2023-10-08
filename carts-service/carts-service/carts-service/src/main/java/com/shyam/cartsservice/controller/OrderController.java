package com.shyam.cartsservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.cartsservice.exception.CartException;
import com.shyam.cartsservice.exception.CustomerException;
import com.shyam.cartsservice.exception.OrderException;
import com.shyam.cartsservice.model.Orders;
import com.shyam.cartsservice.service.OrderService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService oService;

	@GetMapping("/add/{customerId}")
	public ResponseEntity<Orders> addOrder(@PathVariable String customerId)
			throws OrderException, CustomerException, CartException {
		return new ResponseEntity<Orders>(oService.addOrder(customerId), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order) throws OrderException {
		return new ResponseEntity<Orders>(oService.updateOrder(order), HttpStatus.OK);
	}

	@GetMapping("/viewByOrderId/{id}")
	public ResponseEntity<Orders> viewOrderById(@PathVariable("id") Integer orderId) throws OrderException {
		return new ResponseEntity<Orders>(oService.viewOrder(orderId), HttpStatus.OK);
	}

	@GetMapping("/viewAllOrder")
	public ResponseEntity<List<Orders>> viewAllOrder() throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrder(), HttpStatus.OK);
	}

	@GetMapping("/viewByUserId/{userId}")
	public ResponseEntity<List<Orders>> viewOrderByUserId(@PathVariable("userId") String userId)
			throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrdersByUserId(userId), HttpStatus.OK);
	}
}