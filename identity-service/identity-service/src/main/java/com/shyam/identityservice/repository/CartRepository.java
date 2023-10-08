package com.shyam.identityservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shyam.identityservice.entity.Cart;

public interface CartRepository extends MongoRepository<Cart,Integer>{

}
