package com.shyam.productsservice.service;

import java.io.IOException;
import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.productsservice.exception.ProductException;
//import com.shyam.productsservice.model.Category;
import com.shyam.productsservice.model.Product;
//import com.shyam.productsservice.repo.CategoryRepo;
import com.shyam.productsservice.repo.ProductRepo;

@Component
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo pRepo;

//	@Autowired
//	private CategoryRepo cRepo;

	@Override
	public List<Product> viewAllProduct() throws ProductException {
		List<Product> products = pRepo.findAll();
		if (products.size() > 0) {
			return products;
		} else {
			throw new ProductException("Products not found");
		}
	}

//	@Override
//	public Product addProduct(Product product) throws ProductException {
//		Product pro = pRepo.save(product);
//		if (pro != null) {
//			return pro;
//		} else {
//			throw new ProductException("Product not added");
//		}
//
//	}
	
	@Override
	public Product addProduct(MultipartFile image,Integer productId,String productName,double price,String color,String dimension,String specification,String menufacturer,String category) throws ProductException, IOException
	{
		Product product=new Product(productId,productName,price,color,image.getBytes(),dimension,specification,menufacturer,category);
		Product pro = pRepo.save(product);
		if (pro != null) {
			return pro;
		} else {
			throw new ProductException("Product not added");
		}
	}


	@Override
	public Product updateProduct(Product product) throws ProductException {
		Optional<Product> opt = pRepo.findById(product.getProductId());
		if (opt.isPresent()) {
			return pRepo.save(product);

		} else {
			throw new ProductException("Product not updated");
		}

	}

	@Override
	public Product viewProduct(Integer productId) throws ProductException {
		Optional<Product> opt = pRepo.findById(productId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new ProductException("Product not found with product id - " + productId);
		}
	}

//	@Override
//	public List<Product> viewProductByCategory(Integer categoryId) throws ProductException {
//		Optional<Category> category = cRepo.findById(categoryId);
//		if (category.isPresent()) {
//			return category.get().getProductList();
//		} else {
//			throw new ProductException("Product not found with category id - " + categoryId);
//		}
//
//	}
	
	@Override
	public List<Product> viewProductByCategory(String category) throws ProductException {
		List<Product> product = pRepo.findAllByCategory(category);
		if (!product.isEmpty()) {
			return product;
		} else {
			throw new ProductException("Product not found with category - " + category);
		}

	}

	@Override
	public Product removeProduct(Integer productId) throws ProductException {
		Product p = pRepo.findById(productId).orElseThrow(() -> new ProductException("Product not found"));
		pRepo.delete(p);
		return p;

	}
	
	
	
	
	
	
//	@Autowired
//	private ProductRepository productRepo;
//	
//	public void addProducts(Product product) {
//		productRepo.save(product);
//	}
//	public List<Product> getAllProducts(){
//		return productRepo.findAll();
//	}
//	public Product getProductById(int productId){
//		return productRepo.findById(productId).get();
//	}
//	public List<Product> getProductByName(String productName){
//		return productRepo.findByProductName(productName);
//	}
//	public boolean updateProducts(Product product) {
//		Optional<Product> obj=productRepo.findById(product.getProductId());
//		if(obj.isEmpty())
//		{
//			return false;
//		}
//		productRepo.save(product);
//		return true;
//	}
//	public void deleteProductById(int productId) {
//		if(!productRepo.findById(productId).isEmpty()) {
//			productRepo.deleteById(productId);
//		}
//	}
//	
//	public List<Product> getProductByCategory(String category)
//	{
//		return productRepo.findByCategory(category);
//	}
//	public List<Product> getProductByType(String productType){
//		return productRepo.findByProductType(productType);
//	}
}
