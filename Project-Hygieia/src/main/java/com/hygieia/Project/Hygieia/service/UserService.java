package com.hygieia.Project.Hygieia.service;

import com.hygieia.Project.Hygieia.dto.UserRegisterRequest;
import com.hygieia.Project.Hygieia.model.User;
import com.hygieia.Project.Hygieia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String  registerUser(UserRegisterRequest userRegisterRequest) {
        try {
            String userEmail = userRegisterRequest.getEmail();
            if (userEmail != null && userRepository.findUserByEmail(userEmail).isPresent()) {
                return "User with this ID already exists.";
            }
            User savedUser = User.builder()
                    .firstName(userRegisterRequest.getFirstName())
                    .lastName(userRegisterRequest.getLastName())
                    .email(userRegisterRequest.getEmail())
                    .password(userRegisterRequest.getPassword())
                    .documents( new ArrayList<>())
                    .build();
            // Save the user to the repository
            userRepository.save(savedUser);
            return "User registered successfully"
                    + " with ID: " + savedUser.getId()
                    + " and Email: " + savedUser.getEmail();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to register user: " + e.getMessage());
        }
    }

    public ResponseEntity<User> findByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<String> loginUser(String email, String password) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<User> findById(Long id) {
        try {
            Optional<User> user = userRepository.findUserById(id);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (Exception e) {
            throw new RuntimeException("Error finding user with id: " + id, e);
        }
    }
}