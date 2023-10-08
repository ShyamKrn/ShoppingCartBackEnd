package com.shyam.identityservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shyam.identityservice.entity.ConfirmationToken;

@Repository
public interface TokenCodeRepo extends MongoRepository<ConfirmationToken, String>{

}
