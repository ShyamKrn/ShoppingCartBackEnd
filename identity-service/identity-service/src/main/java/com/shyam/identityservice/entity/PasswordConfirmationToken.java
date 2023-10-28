package com.shyam.identityservice.entity;

import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PasswordConfirmationToken {@Id
	private String passConfirmationToken;
	private String userName;
	
	public PasswordConfirmationToken(String userName) {
		this.userName = userName;
//		confirmationToken = UUID.randomUUID().toString();
		Random random = new Random();
		passConfirmationToken = String.format("%04d", random.nextInt(10000));
	}

}