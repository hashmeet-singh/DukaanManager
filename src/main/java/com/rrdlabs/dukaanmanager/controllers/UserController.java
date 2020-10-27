package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.User;
import com.rrdlabs.dukaanmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        if (userService.validateUser(user)) {
            User createdUser = userService.addUser(user);
            return ResponseEntity.ok("User created successfully with Id: " + createdUser.getId());
        }

        return new ResponseEntity("Validation Failed", HttpStatus.BAD_REQUEST);
    }
}
