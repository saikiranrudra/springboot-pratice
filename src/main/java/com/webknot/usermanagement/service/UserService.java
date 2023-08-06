package com.webknot.usermanagement.service;

import com.webknot.usermanagement.model.User;
import com.webknot.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired PasswordEncoder passwordEncoder;

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

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user) {
        if(user.getId() == null) return null;
        Optional<User> userResult = userRepository.findById(user.getId());
        userResult.ifPresent(newUser -> {
            if(user.getEmail() != null) newUser.setEmail(user.getEmail());
            if(user.getUsername() != null) newUser.setUsername(user.getUsername());
            if(user.getRole() != null) newUser.setRole(user.getRole());
            newUser.setUpdatedAt(LocalDateTime.now());
        });

        return userRepository.save(userResult.get());
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
