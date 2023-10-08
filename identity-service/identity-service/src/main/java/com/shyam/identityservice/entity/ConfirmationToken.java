package com.shyam.identityservice.entity;

import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shyam.identityservice.dto.AuthRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ConfirmationToken {
	
	@Id
	private String confirmationToken;
	private AuthRequest authRequest;
	
	public ConfirmationToken(AuthRequest authRequest) {
		this.authRequest = authRequest;
//		confirmationToken = UUID.randomUUID().toString();
		Random random = new Random();
		confirmationToken = String.format("%04d", random.nextInt(10000));
	}
	
}
