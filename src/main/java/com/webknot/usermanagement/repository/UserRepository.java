package com.webknot.usermanagement.repository;

import com.webknot.usermanagement.model.User;
import com.webknot.usermanagement.rowmapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;

@Slf4j
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
        String sql = "INSERT INTO \"user\" (USERNAME,PASSWORD,EMAIL,CREATED_AT,UPDATED_AT) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                user.getUsername(),
                bCryptPasswordEncoder().encode(user.getPassword()),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public int updateUser(User user) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE \"user\" SET ");

        if(user.getEmail() != null) query.append("\"email\" = ? ");
        if(user.getUsername() != null) query.append("\"username\" = ? ");
        if(user.getPassword() != null) query.append("\"password\" = ?");

        query.append("WHERE \"id\" = ?");

        return jdbcTemplate.update(query.toString(), (PreparedStatement prepareStatment) -> {
            int arg = 1;

            if(user.getEmail() != null) prepareStatment.setString(arg++, user.getEmail());
            if(user.getUsername() != null) prepareStatment.setString(arg++, user.getUsername());
            if(user.getPassword() != null) prepareStatment.setString(arg++, bCryptPasswordEncoder().encode(user.getPassword()));
            prepareStatment.setInt(arg, user.getId());
        });

    }

    public List<User> getUserById(int id) {
        String sql = "SELECT * FROM \"user\" WHERE \"id\" = ?";

        return jdbcTemplate.query(
                sql,
                (PreparedStatement preparedStatement) -> preparedStatement.setInt(1, id),
                new UserRowMapper()
        );
    }

    public List<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM \"user\" WHERE \"email\" = ?";

        return jdbcTemplate.query(
                sql,
                (PreparedStatement preparedStatement) -> preparedStatement.setString(1, email),
                new UserRowMapper()
        );
    }

    public int deleteUser(int id) {
        String sql = "DELETE FROM \"user\" WHERE \"id\" = ?";

        return jdbcTemplate.update(
                sql,
                (PreparedStatement prepareStatement) -> prepareStatement.setInt(1, id)
        );
    }
}
