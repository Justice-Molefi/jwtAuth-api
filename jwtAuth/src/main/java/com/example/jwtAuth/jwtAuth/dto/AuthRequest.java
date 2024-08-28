package com.example.jwtAuth.jwtAuth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthRequest(
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Password cannot be empty")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#%*?&_])[A-Za-z\\d@$#!%*?&_]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character")
        String password
) {
}
