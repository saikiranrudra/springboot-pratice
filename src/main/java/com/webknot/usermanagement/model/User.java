package com.webknot.usermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message="User should have username")
    @Column(unique = true)
    private String username;

    @NotBlank(message="User should have a password")
    private String password;

    @NotBlank(message="User should have a email")
    @Email(message="Email format is invalid")
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'USER'")

    private Role role;

    @PrePersist()
    public void prePresist() {
        if(this.role == null) {
            this.role = Role.USER;
        }
    }

    public enum Role {
        ADMIN,
        USER
    }

}
