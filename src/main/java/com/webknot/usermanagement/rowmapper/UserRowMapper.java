package com.webknot.usermanagement.rowmapper;

import com.webknot.usermanagement.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("ID"));
        user.setEmail(rs.getString("EMAIL"));
        user.setUsername(rs.getString("USERNAME"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setCreatedAt(rs.getTimestamp("CREATED_AT").toLocalDateTime());
        user.setUpdatedAt(rs.getTimestamp("UPDATED_AT").toLocalDateTime());
        return user;
    }
}
