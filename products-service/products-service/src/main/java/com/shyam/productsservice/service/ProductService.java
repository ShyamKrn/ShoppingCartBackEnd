package com.shyam.productsservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//import java.util.Optional;

import com.shyam.productsservice.exception.ProductException;
import com.shyam.productsservice.model.Product;

public interface ProductService {
	

		public List<Product> viewAllProduct() throws ProductException;

		//public Product addProduct(Product product) throws ProductException;
		public Product addProduct(MultipartFile image,Integer productId,String productName,double price,String color,String dimension,String specification,String menufacturer,String category) throws ProductException, IOException;

		public Product updateProduct(Product product) throws ProductException;

		public Product viewProduct(Integer productId) throws ProductException;

//		public List<Product> viewProductByCategory(Integer categoryId) throws ProductException;
		
		public List<Product> viewProductByCategory(String category) throws ProductException;

		public Product removeProduct(Integer productId) throws ProductException;
	
//	public void addProducts(Product product);
//	public List<Product> getAllProducts();
//	public Product getProductById(int productId);
//	public List<Product> getProductByName(String productName);
//	public boolean updateProducts(Product product);
//	public void deleteProductById(int productId);
//	public List<Product> getProductByCategory(String category);
//	public List<Product> getProductByType(String productType);
	
}
