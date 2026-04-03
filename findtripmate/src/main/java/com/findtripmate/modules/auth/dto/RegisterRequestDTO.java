package com.findtripmate.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@vitapstudent\\.ac\\.in$",
        message = "Email must be a VIT-AP student email"
    )
    private String email;

    @NotBlank
    private String password;
}