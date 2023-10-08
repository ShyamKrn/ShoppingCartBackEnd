package com.shyam.productsservice.repo;

import java.util.List;

//import java.util.List;
//
//
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shyam.productsservice.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	List<Product> findAllByCategory(String category);
}

//@Repository
//public interface ProductRepository extends MongoRepository<Product, Integer>{
//	List<Product> findByProductName(String productName);
//	List<Product> findByCategory(String category);
//	List<Product> findByProductType(String productType);
//}
