package com.hygieia.Project.Hygieia.controller;


import com.hygieia.Project.Hygieia.dto.UserRegisterRequest;
import com.hygieia.Project.Hygieia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(userRegisterRequest));
    }

    @PostMapping("/getUserByEmailOrId")
    public ResponseEntity<?> getUser(@RequestParam(required = false) String email,
                                     @RequestParam(required = false) Long id) throws Exception {
        if (id != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
        } else if (email != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email));
        } else {
            return ResponseEntity.badRequest().body("Either 'email' or 'id' parameter is required.");
        }
    }

    @PostMapping("/updateUserDocumentList")
    public ResponseEntity<?> updateUserDocumentList(@RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserDocumentList(userId));
    }
    
}
