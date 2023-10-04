package com.example.springboot.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    private String currentPassword;

    @Size(min = 6, max = 32, message = "Password length must be between 6 and 34 characters")
    private String newPassword;
}
