package com.webknot.usermanagement.repository;

import com.webknot.usermanagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
}
