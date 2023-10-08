package com.shyam.cartsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Address {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	private String streetNo;
	private String buildingName;
	private String city;
	private String state;
	private String country;
	private String pincode;
}




//package com.shyam.cartsservice.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Data;
//
//@Data
//@Entity
//public class Address {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer addressId;
//	private String streetNo;
//	private String buildingName;
//	private String city;
//	private String state;
//	private String country;
//	private String pincode;
//}