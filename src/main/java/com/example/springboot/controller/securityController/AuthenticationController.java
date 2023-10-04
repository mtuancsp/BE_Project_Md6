package com.example.springboot.controller.securityController;

import com.example.springboot.configuration.JwtAuthenticationFilter;
import com.example.springboot.dto.request.SignInRequest;
import com.example.springboot.dto.request.SignUpRequest;
import com.example.springboot.dto.response.ApiResponse;
import com.example.springboot.dto.response.JwtAuthenticationResponse;
import com.example.springboot.service.AuthenticationService;
import com.example.springboot.service.googleOAuth2.GoogleOAuth2Service;
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
    private final GoogleOAuth2Service googleOAuth2Service;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/google")
    public ResponseEntity<JwtAuthenticationResponse> signinWithGoogle(@RequestBody String accessToken) {
        System.out.println(accessToken);
        return ResponseEntity.ok(googleOAuth2Service.ggOAuth2Login(accessToken));
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