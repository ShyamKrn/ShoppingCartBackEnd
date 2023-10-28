package com.shyam.cartsservice.model;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class History {

	@Id
	private Integer phId;
	List<Product> products;
	private Date purchaseDate;
}
