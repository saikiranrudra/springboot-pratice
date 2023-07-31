package com.webknot.usermanagement.repository;

import com.webknot.usermanagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByRole(String role);
    User findByUsername(String username);
}
