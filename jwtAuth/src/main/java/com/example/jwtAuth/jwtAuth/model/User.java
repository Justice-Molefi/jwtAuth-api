package com.example.jwtAuth.jwtAuth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private UUID uuid;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
    private Set<Role> roles;
}
