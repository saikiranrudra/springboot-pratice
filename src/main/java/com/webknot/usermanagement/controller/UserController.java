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

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getUserById(@RequestParam(required = false) Integer id,
                                                  @RequestParam(required = false) String email) {
        List<User> users = new ArrayList<>();
        if(id != null) {
            users = userService.getUserById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(users);
        } else if(email != null) {
            users = userService.getUserByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(users);
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
    public ResponseEntity<Response> updateUser(@RequestBody() User user) {

        Response response = new Response();

        if(user.getId() == null) {
            response.setStatus(Response.Status.FAILURE);
            response.setMessage("Id is required for updating user");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        int result = userService.updateUser(user);


        if(result <= 0) {
            response.setStatus(Response.Status.FAILURE);
            response.setMessage("Failed Updating data");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        response.setStatus(Response.Status.SUCCESS);
        response.setMessage("User updated successfully");

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<Response> deleteUser(@RequestParam() Integer id) {
        if(id == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Response("user id is required", Response.Status.FAILURE));


        int result = userService.deleteUser(id);

        if(result <= 0)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Response("Deletion failed", Response.Status.FAILURE));


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response("User deleted successfully", Response.Status.SUCCESS));



    }
}
