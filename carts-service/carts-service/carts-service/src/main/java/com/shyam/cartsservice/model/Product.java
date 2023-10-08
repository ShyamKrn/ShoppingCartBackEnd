package com.shyam.cartsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Product {

	@Id
	private Integer productId;
	private String productName;
	private Double price;
	private String color;
	private byte[] image;
	private String dimension;
	private String specification;
	private String menufacturer;
	private String category;

//	private Category category;
}

//package com.shyam.cartsservice.model;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import lombok.Data;
//
//@Data
//@Embeddable
//@Entity
//public class Product {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer productId;
//	private String productName;
//	private Double price;
//	private String color;
//	private String dimension;
//	private String specification;
//	private String menufacturer;
//
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Category category;
//}