package com.webknot.usermanagement.repository;

import com.webknot.usermanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int createUser(User user) {
        String sql = "INSERT INTO \"USER\" (USERNAME,PASSWORD,EMAIL,CREATED_AT,UPDATED_AT) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                user.getUsername(),
                bCryptPasswordEncoder().encode(user.getPassword()),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}
