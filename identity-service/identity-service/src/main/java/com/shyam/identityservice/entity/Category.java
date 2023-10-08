package com.shyam.identityservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Category {

	@Id
	private Integer catId;
	private String categoryName;
	
	private List<Product> productList;
	
}

//package com.shyam.identityservice.entity;
//
//import java.util.List;
//
//
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import lombok.Data;
//
//@Data
//@Entity
//public class Category {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer catId;
//	private String categoryName;
//	
//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
//	private List<Product> productList;
//	
//}