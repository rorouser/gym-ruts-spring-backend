package com.mygymroutine.persistence.user;

import java.time.LocalDateTime;
import java.util.Date;

import com.mygymroutine.auth.AuthenticationResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	private Integer id;
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String email;
    private Date registrationDate; 
	private int userWeight;
	private int userHeight;
	private String role;
	private byte[] profileImage;

}
