package com.shyam.cartsservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shyam.cartsservice.model.History;

public interface HistoryRepo extends MongoRepository<History, Integer>{

}
