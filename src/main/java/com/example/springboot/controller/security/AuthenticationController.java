package com.example.springboot.controller.security;

import com.example.springboot.configuration.JwtAuthenticationFilter;
import com.example.springboot.dto.request.SignInRequest;
import com.example.springboot.dto.request.SignUpRequest;
import com.example.springboot.dto.response.JwtAuthenticationResponse;
import com.example.springboot.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String token) {
        if (!jwtAuthenticationFilter.isTokenBlacklisted(token)) {
            jwtAuthenticationFilter.addToBlacklist(token);
            return ResponseEntity.ok("Logged out successfully");
        }
        return ResponseEntity.badRequest().body("Logged out. Token has been revoked");
    }

    @GetMapping("/testToken")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World!");
    }
}