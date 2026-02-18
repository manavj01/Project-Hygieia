package com.hygieia.Project.Hygieia.service;

import com.hygieia.Project.Hygieia.dto.DocumentResponse;
import com.hygieia.Project.Hygieia.dto.UserRegisterRequest;
import com.hygieia.Project.Hygieia.dto.UserResponse;
import com.hygieia.Project.Hygieia.exceptionHandling.UserNotFoundException;
import com.hygieia.Project.Hygieia.exceptionHandling.UserResponseBuildException;
import com.hygieia.Project.Hygieia.model.User;
import com.hygieia.Project.Hygieia.repository.DocumentRepository;
import com.hygieia.Project.Hygieia.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(UserRegisterRequest userRegisterRequest) {
        try {
            String userEmail = userRegisterRequest.getEmail();
            if (userEmail != null && userRepository.findUserByEmail(userEmail) != null) {
                return "User with this emailID already exists.";
            }
            // Hash the password before saving to the database
            String hashedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
            User savedUser = User.builder()
                    .firstName(userRegisterRequest.getFirstName())
                    .lastName(userRegisterRequest.getLastName())
                    .email(userRegisterRequest.getEmail())
                    .password(hashedPassword)
                    .documents(new ArrayList<>())
                    .createdAt(LocalDateTime.now())
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

    @Transactional
    public String loginUser(String email, String password) {
        try {
            User user = userRepository.findUserByEmail(email);
            if (user == null) {
                log.warn("User Login Failed - email not found: {}", email);
                throw new UserNotFoundException("User not found with email: " + email);
            }
            if (!passwordEncoder.matches(password, user.getPassword())) {
                log.warn("User Login Failed - Invalid Password: {}", email);
                throw new RuntimeException("Invalid password");
            }
            log.info("User Login Successful: {}", user.getEmail());
            return "Login Successful!";
        } catch (Exception e) {
            log.error("Login failed for user with email: {}", email, e);
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

    @Transactional
    public UserResponse findUserByEmail(String email) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        return getUserResponse(user);
    }


    @Transactional
    public UserResponse findUserById(Long id) {

        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return getUserResponse(user);
    }

    private UserResponse getUserResponse(User user) {

        try {
            List<DocumentResponse> documentResponse = user.getDocuments().stream().map(
                    document -> DocumentResponse.builder()
                            .title(document.getTitle())
                            .description(document.getDescription())
                            .uploadedAt(document.getUploadedAt())
                            .documentCategory(document.getDocumentCategory())
                            .build()
            ).toList();

            return UserResponse.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .documents(documentResponse)
                    .build();
        } catch (Exception e) {
            log.error("Error creating UserResponse", e);
            throw new UserResponseBuildException("Failed to create UserResponse for user id: " + user.getId(), e);
        }
    }


}