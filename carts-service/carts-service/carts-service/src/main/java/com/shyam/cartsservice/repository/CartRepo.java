package com.shyam.cartsservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shyam.cartsservice.model.Cart;

@Repository
public interface CartRepo extends MongoRepository<Cart, Integer> {

}
//package com.shyam.cartsservice.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.shyam.cartsservice.model.Cart;
//
//@Repository
//public interface CartRepo extends JpaRepository<Cart, Integer> {
//
//}