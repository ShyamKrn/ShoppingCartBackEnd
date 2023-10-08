package com.shyam.productsservice.controller;

import java.io.IOException;
import java.util.List;



//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.productsservice.exception.ProductException;
import com.shyam.productsservice.model.Product;
import com.shyam.productsservice.service.ProductService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService pService;

//	@GetMapping("/view")
//	public List<Product> viewAllProduct() throws ProductException {
//		return pService.viewAllProduct();
//	}
	@GetMapping("/view")
	public ResponseEntity<List<Product>> viewAllProduct() throws ProductException {
		return new ResponseEntity<List<Product>>(pService.viewAllProduct(), HttpStatus.OK);
	}
//	@PostMapping("/add")
//	public ResponseEntity<Product> addProduct(@RequestBody Product p) throws ProductException {
//		Product product = pService.addProduct(p);
//		return new ResponseEntity<Product>(product, HttpStatus.OK);
//	}
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestParam("image") MultipartFile image,
			@RequestParam("productId") int productId,
			@RequestParam("productName") String productName,
			@RequestParam("price") double price,
			@RequestParam("color") String color,
			@RequestParam("dimension") String dimension,
			@RequestParam("specification") String specification,
			@RequestParam("menufacturer") String menufacturer,
			@RequestParam("category") String category) throws ProductException, IOException {
		Product product = pService.addProduct(image,productId,productName,price,color,dimension,specification,menufacturer,category);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	

	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product p) throws ProductException {
		Product product = pService.updateProduct(p);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/viewByProductId/{productId}")
	public ResponseEntity<Product> viewProductById(@PathVariable("productId") Integer productId) throws ProductException {
		return new ResponseEntity<Product>(pService.viewProduct(productId), HttpStatus.OK);
	}

//	@GetMapping("/viewByCategoryId/{categoryId}")
//	public ResponseEntity<List<Product>> viewProductByCategoryId(@PathVariable("categoryId") Integer categoryId)
//			throws ProductException {
//		return new ResponseEntity<List<Product>>(pService.viewProductByCategory(categoryId), HttpStatus.OK);
//	}

	
	@GetMapping("/viewByCategoryId/{category}")
	public ResponseEntity<List<Product>> viewProductByCategoryId(@PathVariable("category") String category)
			throws ProductException {
		return new ResponseEntity<List<Product>>(pService.viewProductByCategory(category), HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{productId}")
	public ResponseEntity<Product> removeProductById(@PathVariable("productId") Integer productId)
			throws ProductException {
		return new ResponseEntity<Product>(pService.removeProduct(productId), HttpStatus.OK);
	}

}





//@RestController
//public class ProductController {
//
//	@Autowired
//	private ProductService productService;
//	
//	public void addProducts(Product product);
//	public List<Product> getAllProducts();
//	public Optional<Product> getProductById(int productId);
//	public Optional<Product> getProductByName(String productName);
//	public boolean updateProducts(Product product);
//	public void deleteProductById(int productId);
//	public List<Product> getProductByCategory(String category);
//	public List<Product> getProductByType(String productType);
	
	
//	@PostMapping("/addProducts")
//	public String addProducts(@RequestBody Product product)
//	{
//		productService.addProducts(product);
//		return "Product added Successfully";
//		
//	}
//	
//	@GetMapping("/getAllProducts")
//	public List<Product> getAllProducts() {
//		return productService.getAllProducts();
//	}
//	
//	@GetMapping("/getProductbyId/{productId}")
//	public Product getProductById(@PathVariable Integer productId)
//	{
//		return productService.getProductById(productId);
//	}
//	
//	@GetMapping("/getProductByName/{productName}")
//	public List<Product> getProductByName(@PathVariable String productName) 
//	{
//		return productService.getProductByName(productName);
//	}
//	
//	@PutMapping("/updateProducts")
//	public String updateProducts(@RequestBody Product product) {
//		if(productService.updateProducts(product)) {
//			return "Product Updated";
//		}
//		return "Product not Updated, Try again later";
//	}
//	
//	@DeleteMapping("/deleteProduct/{productId}")
//	public String deleteProductById(@PathVariable int productId) 
//	{
//		productService.deleteProductById(productId);
//		return "Product deleted";
//	}
//	
//	@GetMapping("/getProductByCategory/{category}")
//	public List<Product> getProductByCategory(@PathVariable String category) {
//		return productService.getProductByCategory(category);
//	}
//	
//	@GetMapping("/getProduct/{productType}")
//	public List<Product> getProductByType(@PathVariable String productType)
//	{
//		return productService.getProductByType(productType);
//	}
//	
//}
