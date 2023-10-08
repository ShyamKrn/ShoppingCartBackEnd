package com.shyam.identityservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shyam.identityservice.dto.AuthRequest;
import com.shyam.identityservice.entity.Cart;
import com.shyam.identityservice.entity.ConfirmationToken;
import com.shyam.identityservice.entity.Customer;
import com.shyam.identityservice.repository.AuthRepo;
import com.shyam.identityservice.repository.CartRepository;
import com.shyam.identityservice.repository.TokenCodeRepo;
import com.shyam.identityservice.repository.UserCredentialRepository;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private CartRepository cartRepo; 
    
    @Autowired
    private AuthRepo authRepo;
    
    @Autowired
    private TokenCodeRepo tokenRepo;
    
    @Autowired
    private EmailService emailService;
    
    
    public boolean sendVerificationCode(AuthRequest authRequest) {

		Optional<AuthRequest> obj = authRepo.findById(authRequest.getUsername());
		if(!obj.isEmpty()) {
			return false;
		}
		else {
			ConfirmationToken confirmationToken  = new ConfirmationToken(authRequest);
			tokenRepo.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(authRequest.getUsername());
			mailMessage.setSubject("Complete Registration");
			mailMessage.setText("To confirm your account, enter this code : " +
								confirmationToken.getConfirmationToken());
			emailService.sendMail(mailMessage);
			return true;
		}
		
	}
    
    public boolean verifyAccount(String confirmationToken) {
		Optional<ConfirmationToken> obj = tokenRepo.findById(confirmationToken);
		if(!obj.isEmpty()) {
			ConfirmationToken token = obj.get();
			authRepo.save(token.getAuthRequest());
			return true;
		}
		return false;
	}

    public String saveUser(Customer credential) {
  
    	 AuthRequest authRequestObj = new AuthRequest();
         authRequestObj.setUsername(credential.getEmail());
         authRequestObj.setPassword(credential.getPassword());
         authRequestObj.setRoles(credential.getRoles());
         authRepo.save(authRequestObj);
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        
        
        	List<Customer> customers=new ArrayList<>();
        	customers=repository.findAll();

        	    boolean uniqueCartIdFound = false;

        	    for (int i = 1; i < 100; i++) {
        	        boolean cartIdUsed = false;
        	        
        	        for (Customer existingCustomer : customers) {
        	            if (existingCustomer.getCart() != null && existingCustomer.getCart().getCartId() == i) {
        	                cartIdUsed = true;
        	                break; // The cartId is already used by another customer
        	            }
        	        }
        	        
        	        if (!cartIdUsed) {
        	            Cart cart = new Cart();
        	            cart.setCartId(i);
        	            cart.setProduct_quantity(0);
        	            credential.setCart(cart);
        	            uniqueCartIdFound = true;
        	            break;
        	        }
        	    }
        	    
        	    if (!uniqueCartIdFound) {
        	        // Handle the case where no unique cartId was found for this customer
        	    	
        	    }

        repository.save(credential);
        return "user added to the system";
    }
    
    public Customer getCustomerById(String cId) {
    	return repository.findById(cId).get();
    }
    
    
    public boolean validateUserDetail(AuthRequest authRequest) {
		Optional<AuthRequest> obj = authRepo.findById(authRequest.getUsername());
		if(obj.isEmpty()) {
			return false;
		}
		
		if(!obj.get().getPassword().equals(authRequest.getPassword())) {
			return false;
		}
		return true;
	}
    
    public List<Customer> getAllCustomer()
    {
    	return repository.findAll();
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateByToken(token);
    }


}
