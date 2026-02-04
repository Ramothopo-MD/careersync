package com.career.CareerSync.DTO;

import com.career.CareerSync.Models.Role;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "First name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Role is required")
    private Role role;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String profileUrl;

    // Optional phone number, validate only if not empty
    @Pattern(regexp = "^(|\\+?\\d{10,15})$", message = "Phone number must be 10-15 digits")
    private String phoneNumber;

    /* =========================
       SECURITY / AUTH FIELDS
       ========================= */

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank(message = "Security question is required")
    private String securityQuestion;

    @NotBlank(message = "Security answer is required")
    private String securityAnswer;
}
