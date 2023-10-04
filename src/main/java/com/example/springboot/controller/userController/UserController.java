package com.example.springboot.controller.userController;

import com.example.springboot.dto.request.ChangePasswordRequest;
import com.example.springboot.model.Booking;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;

import com.example.springboot.service.bookingService.IBookingService;
import com.example.springboot.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IBookingService bookingService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<User> detailUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/apply-host")
    public ResponseEntity<String> applyHost() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.applyHost(username);
    }


    //@Bean PasswordEncoder sẽ bị gọi thành vòng lặp nếu đặt trong userService nên tạm thời viết luôn ở controller
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        User user = userService.getCurrentUser();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body("Invalid current password");

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body("New password must be different from the current password");

        String newPasswordEncoded = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(newPasswordEncoded);
        userRepository.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }

    @PutMapping("/current")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User updatedUser) {
        User currentUser = userService.updateUser(updatedUser);
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/list-booking")
    public ResponseEntity<List<Booking>> getBookingsByUser() {
        User user = userService.getCurrentUser();
        return new ResponseEntity<>(bookingService.getBookingsByUser(user),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        if (userService.getCurrentUser().getRole() == Role.ADMIN) {
            try {
                return new ResponseEntity<>((List<User>) userService.findAll(), HttpStatus.OK);
            } catch (NullPointerException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
