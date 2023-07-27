package com.webknot.usermanagement.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class User extends BaseEntity {
    @NotBlank(message="User should have a Id")
    private int id;

    @NotBlank(message="User should have username")
    @UniqueElements(message="User should have unique username")
    private String username;

    @NotBlank(message="User should have a password")
    private String password;

    @NotBlank(message="User should have a email")
    @UniqueElements(message="User should have unique email")
    @Email(message="Email format is invalid")
    private String email;

}
