package com.webknot.usermanagement.service;

import com.webknot.usermanagement.model.User;
import com.webknot.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    /**
     * Create a new user in user table
     * @param user
     * @return user
     */
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        int result = userRepository.createUser(user);

        if(result <= 0) {
            return null;
        }
        return user;
    }

}
