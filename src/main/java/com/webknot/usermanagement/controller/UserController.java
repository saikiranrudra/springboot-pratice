package com.webknot.usermanagement.controller;

import com.webknot.usermanagement.model.User;
import com.webknot.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<User> getUserById(@RequestParam(required = true) String id) {
        User firstUser = new User();
        firstUser.setUsername("My Username");
        firstUser.setEmail("saikiranrudra2@gmail.com");
        firstUser.setId(2);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(firstUser);
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody() User user) {

        User createdUser = userService.createUser(user);

        if(createdUser == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }
}
