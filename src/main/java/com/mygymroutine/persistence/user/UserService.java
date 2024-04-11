package com.mygymroutine.persistence.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

	public UserResponse getUserById(Integer userId) {
	    Optional<User> user = userRepository.findById(userId);
	    return user.map(value ->
	            UserResponse.builder()
	                    .firstName(value.getFirstname())
	                    .lastName(value.getLastname())
	                    .email(value.getEmail())
	                    .registrationDate(value.getRegistrationDate())
	                    .build())
	            .orElse(null);
	}
    
    public Optional<User> getUserByIdCheck(Integer userId) {
        return userRepository.findById(userId);
    }

    public UserResponse updateUser(Integer userId, User updatedUser) {
    	Optional<User> existingUser = getUserByIdCheck(userId);
    	
    	 if (existingUser.isPresent() && 
    			 (existingUser.get().getId() == userId || existingUser.get().getId() == 1)) {
             updatedUser.setId(userId);
             updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
             User savedUser = userRepository.save(updatedUser);

             UserResponse userResponse = UserResponse.builder()
             		 .id(savedUser.getId())
                     .firstName(savedUser.getFirstname())
                     .lastName(savedUser.getLastname())
                     .email(savedUser.getEmail())
                     .registrationDate(savedUser.getRegistrationDate())
                     .userWeight(savedUser.getUserWeight())
                     .userHeight(savedUser.getUserHeight())
                     .build();
             return userResponse;
             
    	 } else {
    		return null; 
    	 } 
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
    
    public List<UserResponse> findAll(String userRole) {
    	if(userRole.equals("ADMIN")) {
    		List<User> userList = userRepository.findAll();
            List<UserResponse> userResponses = userList.stream()
                    .map(user -> UserResponse.builder()
                    		.id(user.getId())
                            .firstName(user.getFirstname())
                            .email(user.getEmail())
                            .lastName(user.getLastname())
                            .secondLastName(user.getSecondLastname())
                            .registrationDate(user.getRegistrationDate())
                            .userHeight(user.getUserHeight())
                            .userWeight(user.getUserWeight())
                            .role(user.getRole().toString())
                            .build())
                    .collect(Collectors.toList());
    	return userResponses;
    } else {
    	return null;
    }
    	
    }
}

