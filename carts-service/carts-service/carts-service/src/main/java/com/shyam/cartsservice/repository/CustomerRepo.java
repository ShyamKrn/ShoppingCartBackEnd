package com.shyam.cartsservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shyam.cartsservice.model.Customer;
import com.shyam.cartsservice.model.Orders;

@Repository
public interface CustomerRepo extends MongoRepository<Customer, String> {

	public List<Orders> findAllOrderByCId(String cId);

	public Customer findByEmail(String email);
}

//package com.shyam.cartsservice.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.shyam.cartsservice.model.Customer;
//import com.shyam.cartsservice.model.Orders;
//
//@Repository
//public interface CustomerRepo extends JpaRepository<Customer, Integer> {
//
//	@Query("select c.orders from Customer c where c.cId=?1")
//	public List<Orders> getAllOrderByCid(Integer cId);
//
//	public Customer findByEmail(String email);
//}