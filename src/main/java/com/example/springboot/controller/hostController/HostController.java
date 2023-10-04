package com.example.springboot.controller.hostController;

import com.example.springboot.model.User;
import com.example.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/host")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HostController {
    @Autowired
    private UserService userService;

}
