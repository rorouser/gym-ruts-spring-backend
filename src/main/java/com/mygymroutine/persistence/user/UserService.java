package com.mygymroutine.persistence.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
    
    public List<User> findAll() {
    	return userRepository.findAll();
    }
}

