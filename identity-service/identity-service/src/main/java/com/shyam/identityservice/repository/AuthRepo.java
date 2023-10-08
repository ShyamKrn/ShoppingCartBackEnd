package com.shyam.identityservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shyam.identityservice.dto.AuthRequest;

@Repository
public interface AuthRepo extends MongoRepository<AuthRequest, String>{

}
