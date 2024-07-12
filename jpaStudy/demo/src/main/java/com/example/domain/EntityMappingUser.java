package com.example.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EntityMappingUser {

    @Id
    private String email;
    public String getEmail() {
        return email;
    }
    
    private String name;
    public String getName() {
        return name;
    }

    private LocalDateTime createDate;
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public EntityMappingUser() {
    }
    
    public EntityMappingUser(String email, String name, LocalDateTime createDate) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }
}
