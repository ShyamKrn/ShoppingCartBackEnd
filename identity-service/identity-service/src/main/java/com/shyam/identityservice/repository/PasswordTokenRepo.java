package com.shyam.identityservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shyam.identityservice.entity.PasswordConfirmationToken;


public interface PasswordTokenRepo extends MongoRepository<PasswordConfirmationToken, String> {

}