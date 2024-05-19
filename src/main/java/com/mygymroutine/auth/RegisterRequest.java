package com.mygymroutine.auth;

import java.util.Date;

import com.mygymroutine.persistence.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private int height;
	
	private int weight;
	
	private Role role;
	
	private String password;
	
	private String password2;

}
