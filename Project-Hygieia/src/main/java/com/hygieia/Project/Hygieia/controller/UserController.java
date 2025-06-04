package com.hygieia.Project.Hygieia.controller;


import com.hygieia.Project.Hygieia.model.User;
import com.hygieia.Project.Hygieia.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(user).getBody());
    }
    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(email));
    }
}
