package com.webknot.usermanagement.controller;

import com.webknot.usermanagement.model.Response;
import com.webknot.usermanagement.model.User;
import com.webknot.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<User> getUserById(@RequestParam(required = false) Integer id,
                                                  @RequestParam(required = false) String email) {
        try {
            if(id != null) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(userService.getUserById(id));
            } else if(email != null) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(userService.getUserByEmail(email));
            }
        } catch (NoSuchElementException e) { }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody() User user) {

        if(user.getId() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        User updateUser = userService.updateUser(user);


        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(updateUser);
        }


        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(updateUser);
    }

    @DeleteMapping("")
    public ResponseEntity<Response> deleteUser(@RequestParam() Integer id) {
        if(id == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Response("user id is required", Response.Status.FAILURE));


        userService.deleteUser(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response("User deleted successfully", Response.Status.SUCCESS));

    }
}
