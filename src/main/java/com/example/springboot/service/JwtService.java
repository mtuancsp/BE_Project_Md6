package com.example.springboot.service;

import com.example.springboot.dto.response.JwtAuthenticationResponse;
import com.example.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    Long extractExpirationTime(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    JwtAuthenticationResponse responseJWT(User user);

}