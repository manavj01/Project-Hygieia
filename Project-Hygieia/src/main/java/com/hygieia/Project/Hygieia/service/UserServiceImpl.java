package com.hygieia.Project.Hygieia.service;

import com.hygieia.Project.Hygieia.dto.UserRegisterRequest;
import com.hygieia.Project.Hygieia.dto.UserResponse;
import com.hygieia.Project.Hygieia.model.Document;
import com.hygieia.Project.Hygieia.model.User;
import com.hygieia.Project.Hygieia.repository.DocumentRepository;
import com.hygieia.Project.Hygieia.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    public String  registerUser(UserRegisterRequest userRegisterRequest) {
        try {
            String userEmail = userRegisterRequest.getEmail();
            if (userEmail != null && userRepository.findUserByEmail(userEmail) != null) {
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

    public ResponseEntity<String> loginUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    public UserResponse findUserByEmail(String email) {
        try {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }

            User user = userRepository.findUserByEmail(email);
            if (user == null) {
                throw new RuntimeException("User not found with email: " + email);
            }
            List<Document> documents = fetchDocumentsForUser(user);
            return UserResponse.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .documents(documents)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error finding user with email: " + email, e);
        }
    }



    public UserResponse findUserById(Long id) {
        try {
            User user = userRepository.findUserById(id);
            if (user == null) {
                throw new RuntimeException("User not found with id: " + id);
            }
            user.setDocuments(fetchDocumentsForUser(user));
            return UserResponse.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .documents(user.getDocuments())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error finding user with id: " + id, e);
        }
    }

    /**
     * Fetches documents for a given user and updates the user's document list.
     * If the user is null, returns an empty list.
     *
     * @param user The user for whom to fetch documents.
     * @return A list of documents associated with the user.
     */
    private List<Document> fetchDocumentsForUser(User user) {

        if (user == null) {
            return new ArrayList<>();
        }
        List<Document> documents = documentRepository.findDocumentsByUserId(user.getId());
        user.setDocuments(documents);
        userRepository.save(user);
        if (documents != null && !documents.isEmpty()) {
            return documents;
        } else {
            return new ArrayList<>();
        }
    }

    public String updateUserDocumentList(Long userId) {
        try {
            User user = userRepository.findUserById(userId);
            if (user == null) {
                return "User not found";
            }
            List<Document> documents = documentRepository.findDocumentsByUserId(userId);
            if (documents == null || documents.isEmpty()) {
                return "No documents found for user with ID: " + userId;
            }
            user.setDocuments(documents);
            userRepository.save(user);
            log.info("User document list updated successfully for user with ID: {}", userId);
            return "User document list updated successfully for user with ID: " + userId;
        } catch (Exception e) {
            return "Failed to update user document list: " + e.getMessage();
        }
    }
}