package com.webknot.usermanagement.service;

import com.webknot.usermanagement.model.User;
import com.webknot.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<User> getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public List<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public int updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public int deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

}
