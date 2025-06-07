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
    @GetMapping()
    public ResponseEntity<?> getUser(@RequestParam(required = false) String email,
                                     @RequestParam(required = false) Long id) throws Exception {
        if (email != null) {
            return ResponseEntity.ok(userService.findByEmail(email));
        } else if (id != null) {
            return ResponseEntity.ok(userService.findById(id));
        } else {
            return ResponseEntity.badRequest().body("Either 'email' or 'id' parameter is required.");
        }
    }

    
}
