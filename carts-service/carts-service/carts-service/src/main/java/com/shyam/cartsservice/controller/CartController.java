package com.shyam.cartsservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shyam.cartsservice.exception.CartException;
import com.shyam.cartsservice.exception.CustomerException;
import com.shyam.cartsservice.exception.ProductException;
import com.shyam.cartsservice.model.Cart;
import com.shyam.cartsservice.model.Customer;
import com.shyam.cartsservice.model.History;
import com.shyam.cartsservice.model.Product;
import com.shyam.cartsservice.repository.CartRepo;
import com.shyam.cartsservice.repository.CustomerRepo;
import com.shyam.cartsservice.repository.HistoryRepo;
import com.shyam.cartsservice.repository.ProductRepo;
import com.shyam.cartsservice.service.CartService;

import jakarta.ws.rs.PATCH;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cService;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private HistoryRepo hrepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CustomerRepo cusRepo;
	
	@PostMapping("/addProductToHistory/{customerId}")
	public History addProductToHistory(@PathVariable String customerId ) {
		return cService.addProductToHistory(customerId);
	} 
	@GetMapping("/getHistoryProducts/{customerId}")
	public List<Product> getHistoryProducts(@PathVariable String customerId){
		Customer customerObj = cusRepo.findById(customerId).get();
		int cartId = customerObj.getCart().getCartId();
		
		History historyObj = hrepo.findById(cartId).get();
		
		List<Product> productList = new ArrayList<>();
		productList = historyObj.getProducts();
		return productList;
	}
	
	@GetMapping("/addToDatabase")
	public void addToDatabase() {
		 ResponseEntity<List<Product>> response = restTemplate.exchange(
			        "http://localhost:8081/products/view",
			        HttpMethod.GET,
			        null,
			        new ParameterizedTypeReference<List<Product>>() {});

			    List<Product> products = response.getBody();
			    for (Product product : products) {
			    	Optional<Product> existingProduct = prepo.findById(product.getProductId());
			        if (!existingProduct.isPresent()) {
			            prepo.save(product);
			        }
			    }
			    
			    ResponseEntity<List<Customer>> secresponse = restTemplate.exchange(
				        "http://localhost:9898/auth/customers",
				        HttpMethod.GET,
				        null,
				        new ParameterizedTypeReference<List<Customer>>() {});

				    List<Customer> customers = secresponse.getBody();
				    for (Customer customer : customers) {
				    	Optional<Customer> existingCustomer = cusRepo.findById(customer.getCId());
				       
				    	if (!existingCustomer.isPresent()) {
			//	        	Cart cart = new Cart();
//				        	cart.setCartId(2);
//				            cart.setProduct_quantity(0); // Set the initial quantity as needed
//				            cart.setCustomer(customer); // Set the customer reference
//				            
//				            // Save the Cart instance
//				            cartRepo.save(cart);
//				            
//				            customer.setCart(cart);
				            cusRepo.save(customer);
				        }
				    }
		
		
	}

	//RequestParam("customerId"), @RequestParam("productId")
	@PostMapping("/add/{cId}/{productId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable String cId,
			@PathVariable Integer productId) throws CartException, CustomerException, ProductException {
		
		addToDatabase();
//		 ResponseEntity<List<Product>> response = restTemplate.exchange(
//			        "http://localhost:8081/products/view",
//			        HttpMethod.GET,
//			        null,
//			        new ParameterizedTypeReference<List<Product>>() {});
//
//			    List<Product> products = response.getBody();
//			    for (Product product : products) {
//			    	Optional<Product> existingProduct = prepo.findById(product.getProductId());
//			        if (!existingProduct.isPresent()) {
//			            prepo.save(product);
//			        }
//			    }
//			    
//			    ResponseEntity<List<Customer>> secresponse = restTemplate.exchange(
//				        "http://localhost:9898/auth/customers",
//				        HttpMethod.GET,
//				        null,
//				        new ParameterizedTypeReference<List<Customer>>() {});
//
//				    List<Customer> customers = secresponse.getBody();
//				    for (Customer customer : customers) {
//				    	Optional<Customer> existingCustomer = cusRepo.findById(customer.getCId());
//				       
//				    	if (!existingCustomer.isPresent()) {
//			//	        	Cart cart = new Cart();
////				        	cart.setCartId(2);
////				            cart.setProduct_quantity(0); // Set the initial quantity as needed
////				            cart.setCustomer(customer); // Set the customer reference
////				            
////				            // Save the Cart instance
////				            cartRepo.save(cart);
////				            
////				            customer.setCart(cart);
//				            cusRepo.save(customer);
//				        }
//				    }
		return new ResponseEntity<Cart>(cService.addProductToCart(cId, productId), HttpStatus.OK);

	}
	
	@GetMapping("/getProductList/{cId}")
	public List<Product> getProductList(@PathVariable String cId){
		return cService.getProductList(cId);
	}
	
	@GetMapping("/getCustomerId/{email}")
	public ResponseEntity<Map<String, String>> getCustomerId(@PathVariable String email) {
        String customerId = cService.getCustomerId(email);
        
        Map<String, String> response = new HashMap<>();
        response.put("customerId", customerId);
        
        return ResponseEntity.ok(response);
    }
//	public String getCustomerId(@PathVariable String email) {
//		return cService.getCustomerId(email);
//	}

	@GetMapping("/remove/{cartId}/{productId}")
	public ResponseEntity<Cart> removeProductFromCart(@PathVariable("cartId") String cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.removeProductFromCart(cartId, productId), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{cartId}")
	public ResponseEntity<Cart> removeAllProduct(@PathVariable("cartId") String cartId)
			throws CartException, CustomerException {
		return new ResponseEntity<Cart>(cService.removeAllProduct(cartId), HttpStatus.OK);
	}

	@PutMapping("/increase/{cartId}/{productId}")
	public ResponseEntity<Cart> increaseProductQuantity(@PathVariable("cartId") String cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.increaseProductQuantity(cartId, productId), HttpStatus.OK);
	}

	@PutMapping("/decrease/{cartId}/{productId}")
	public ResponseEntity<Cart> decreaseProductQuantity(@PathVariable("cartId") String cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.decreaseProductQuantity(cartId, productId), HttpStatus.OK);
	}
}
