package com.example.springboot.service;


import com.example.springboot.dto.request.SignInRequest;
import com.example.springboot.dto.request.SignUpRequest;
import com.example.springboot.dto.response.ApiResponse;
import com.example.springboot.dto.response.JwtAuthenticationResponse;
import com.example.springboot.model.User;

public interface AuthenticationService {
    ApiResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);

}