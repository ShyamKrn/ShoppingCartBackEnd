package com.shyam.identityservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Customer {

	@Id
	private String cId;
	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
	private String password;	
	
	private String roles;

	//(cascade = CascadeType.ALL)
	private Address address;
	
//	@JsonIgnore
	//(cascade = CascadeType.ALL)
	private Cart cart;
	
	private List<Orders> orders;
	
}

//"address":{
//    "addressId":1,
//    "streetNo":"abc",
//    "buildingName":"palace",
//    "city":"Kottayam",
//    "state":"Kerala",
//    "country":"India",
//    "pincode":"675746"
//},
//"cart":{
//    "cartId":1,
//    "product_quantity":0
//}

//package com.shyam.identityservice.entity;
//
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import lombok.Data;
//
//@Data
//@Entity
//public class Customer {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer cId;
//	private String firstName;
//	private String lastName;
//	private String mobile;
//	private String email;
//	private String password;	
//
//	@OneToOne
//	//(cascade = CascadeType.ALL)
//	private Address address;
//	
//	@JsonIgnore
//	@OneToOne
//	//(cascade = CascadeType.ALL)
//	private Cart cart;
//	
//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
//	private List<Orders> orders;
//	
//}
//
////"address":{
////    "addressId":1,
////    "streetNo":"abc",
////    "buildingName":"palace",
////    "city":"Kottayam",
////    "state":"Kerala",
////    "country":"India",
////    "pincode":"675746"
////},
////"cart":{
////    "cartId":1,
////    "product_quantity":0
////}
