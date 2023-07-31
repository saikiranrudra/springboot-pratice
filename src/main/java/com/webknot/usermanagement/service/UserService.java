package com.webknot.usermanagement.service;

import com.webknot.usermanagement.model.User;
import com.webknot.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Create a new user in user table
     * @param user
     * @return user
     */
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);

        return createdUser;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user) {

        Optional<User> oldUser = userRepository.findById(user.getId());
        oldUser.ifPresent(currentUser -> {
            if(user.getEmail() != null) currentUser.setEmail(user.getEmail());
            if(user.getRole() != null) currentUser.setRole(user.getRole());
            currentUser.setUpdatedAt(LocalDateTime.now());
        });

        User updatedUser = userRepository.save(oldUser.get());
        return updatedUser;
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
