package com.shyam.identityservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.identityservice.dto.AuthRequest;
import com.shyam.identityservice.entity.Address;
//import com.shyam.identityservice.entity.Cart;
import com.shyam.identityservice.entity.Customer;
import com.shyam.identityservice.repository.AddressRepository;
import com.shyam.identityservice.repository.AuthRepo;
//import com.shyam.identityservice.repository.CartRepository;
import com.shyam.identityservice.repository.UserCredentialRepository;
import com.shyam.identityservice.service.AuthService;
import com.shyam.identityservice.service.JwtService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    
    @Autowired
    private AuthRepo authRepo;
    
    @Autowired
    UserCredentialRepository customerRepository; 
    
    @Autowired
    private AddressRepository addRepo;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
    
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomer() 
    {
    	return new ResponseEntity<List<Customer>>(service.getAllCustomer(), HttpStatus.OK);
    }
    
    @GetMapping("/customerById/{cId}")
    public Customer getCustomerById(@PathVariable String cId) {
    	return service.getCustomerById(cId);
    }
    
    @PostMapping("/addAddress")
    public void addAddress(@RequestBody Address address) {
    	addRepo.save(address);
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody Customer user) {
  
        return service.saveUser(user);
    }
    
    @PostMapping("/validate")
	public boolean validateUserDetail(@RequestBody AuthRequest authRequest) {
		return service.validateUserDetail(authRequest);
	}
    
	@PostMapping("/sendVerificationCode")
	public boolean sendVerificationCode(@RequestBody AuthRequest authRequest) {
		return service.sendVerificationCode(authRequest);
	}
	
	@GetMapping("/verifyAccount/{token}")
	public boolean verifyAccount(@PathVariable("token") String confirmationToken) {
		return service.verifyAccount(confirmationToken);
	}
	
	@PostMapping("/generatetoken")
	public AuthRequest authenticateAndGetToken(@RequestBody AuthRequest authRequest){ 
		//for token creation
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			authRequest.setToken(jwtService.generateToken(authRequest.getUsername()));
			authRequest.setRoles(authRepo.findById(authRequest.getUsername()).get().getRoles());
			return authRequest;
			
		}
		else {
			throw new UsernameNotFoundException("invalid user request!");
		}
	}
	
	@PostMapping("/validateToken")
	public String validateToken(@RequestParam String token) {
		jwtService.validateByToken(token);
		return "Token is valid";
	}
}



//@PostMapping("/addCart")
//public void addAddress(@RequestBody Cart cart) {
//	cartRepo.save(cart);
//}

//@PostMapping("/createCart")
//public void createCart(@RequestParam("customerId") Integer customerId) {
//  // Retrieve the customer based on the customerId (You may need to implement this logic)
//  Customer customer = customerRepository.findById(customerId).get();
//
//  // Create a new Cart instance
//  Cart cart = new Cart();
//  cart.setProduct_quantity(0); // Set the initial quantity as needed
//  cart.setCustomer(customer); // Set the customer reference
//  
//  // Save the Cart instance
//  cartRepo.save(cart);
//}


//@PostMapping("/token")
//public String getToken(@RequestBody AuthRequest authRequest) {
//  Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//  if (authenticate.isAuthenticated()) {
//      return service.generateToken(authRequest.getUsername());
//  } else {
//      throw new RuntimeException("invalid access");
//  }
//}

//@GetMapping("/validate")
//public String validateToken(@RequestParam("token") String token) {
//  service.validateToken(token);
//  return "Token is valid";
//}