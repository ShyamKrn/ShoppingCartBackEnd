package com.shyam.cartsservice.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Orders {

	@Id
	private Integer orderId;
	private LocalDateTime date;
	private String orderStatus;
	private double totalPrice; 

	private Customer customer;

	private List<Product> productList;
	
	private Address address;
}

//package com.shyam.cartsservice.model;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.Embedded;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
//import lombok.Data;
//
//@Data
//@Entity
//public class Orders {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer orderId;
//	private LocalDateTime date;
//	private String orderStatus;
//	private double totalPrice; 
//
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Customer customer;
//
//	@Embedded
//	@ElementCollection
//	private List<Product> productList;
//
//	@JsonIgnore
//	@OneToOne
//	//(cascade = CascadeType.ALL)
//	private Address address;
//}