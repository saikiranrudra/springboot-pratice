package com.webknot.usermanagement.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
