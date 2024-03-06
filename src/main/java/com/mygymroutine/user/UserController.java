package com.mygymroutine.user;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> userList = userService.findAll();
        List<UserResponse> userResponses = userList.stream()
                .map(user -> UserResponse.builder()
                		.id(user.getId())
                        .firstName(user.getFirstname())
                        .email(user.getEmail())
                        .lastName(user.getLastname())
                        .registrationDate(user.getRegistrationDate())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId) {
        Optional<User> user = userService.getUserById(userId);

        return user.map(value -> {
            UserResponse userResponse = UserResponse.builder()
                    .firstName(value.getFirstname())
                    .lastName(value.getLastname())
                    .email(value.getEmail())
                    .registrationDate(value.getRegistrationDate())
                    .build();
            log.info(userResponse.toString());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer userId, @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.getUserById(userId);

        if (existingUser.isPresent()) {
            updatedUser.setId(userId);
            User savedUser = userService.updateUser(updatedUser);

            UserResponse userResponse = UserResponse.builder()
            		.id(savedUser.getId())
                    .firstName(savedUser.getFirstname())
                    .lastName(savedUser.getLastname())
                    .email(savedUser.getEmail())
                    .registrationDate(savedUser.getRegistrationDate())
                    .build();

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //De momento no funciona
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        Optional<User> user = userService.getUserById(userId);

        if (user.isPresent()) {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

