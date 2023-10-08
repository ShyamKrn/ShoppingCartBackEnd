package com.shyam.identityservice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AuthRequest {
	
	@Id
    private String username;
    private String password;
    private String roles;
    private String token;

}
