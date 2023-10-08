package com.shyam.identityservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shyam.identityservice.entity.Customer;

@Repository
public interface UserCredentialRepository  extends MongoRepository<Customer,String> {
    Optional<Customer> findByEmail(String username);
}
