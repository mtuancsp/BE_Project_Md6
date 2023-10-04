package com.example.springboot.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String username;

    @Size(min = 6, max = 32, message = "Password length must be between 6 and 34 characters")
    private String password;

    @Pattern(regexp = "^(0)\\d{9}$", message = "Phone number must start with 0 and have 10 digits in total")
    private String phoneNumber;

    @Email
    private String email;
}
