package com.example.springboot.service.user;

import com.example.springboot.dto.request.ChangePasswordRequest;
import com.example.springboot.exception.NotFoundException;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;

import com.example.springboot.service.house.IHouseService;
import com.example.springboot.service.mail.EmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private IHouseService houseService;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllHosts() {
        return userRepository.findAllByRole(Role.HOST);
    }

    public ResponseEntity<String> applyHost(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        user.setApplyHost(true);
        userRepository.save(user);

        return ResponseEntity.ok("Apply host successfully");
    }

    @Override
    public User acceptHost(Long id, String message) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRole().equals(Role.ADMIN)) return null;
            user.setRole(Role.HOST);
            user.setApplyHost(false);
            userRepository.save(user);
            String to = user.getEmail();
            String subject = "Accept Host";
            emailService.sendSimpleEmail(to, subject, message);
            return user;
        }
        return null;
    }

    @Override
    public User rejectHost(Long id, String message) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setApplyHost(false);
            userRepository.save(user);
            String to = user.getEmail();
            String subject = "Reject Host";
            emailService.sendSimpleEmail(to, subject, message);
            return user;
        }
        return null;
    }

    @Override
    public User blockUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBlocked(true);
            userRepository.save(user);
            String to = user.getEmail();
            String subject = "your account is locked";
            String text = "So sorry. You violated some community terms so your account has been locked. Please contact admin for further assistance.";
            emailService.sendSimpleEmail(to, subject, text);
            return user;
        }
        return null;
    }

    @Override
    public User unlockUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRole().equals(Role.ADMIN)) return null;
            user.setBlocked(false);
            userRepository.save(user);
            String to = user.getEmail();
            String subject = "UnLock Account";
            String text = "Welcome to HomeLand. You are sign in";
            emailService.sendSimpleEmail(to, subject, text);
            return user;
        }
        return null;
    }



    @Override
    public List<User> getUsersWithApplyHost() {
        return userRepository.findByApplyHost(true);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> getHostUsersWithHouseCount() {
        List<User> hostUserList = userRepository.findHostUsers(Role.HOST);

        List<Map<String, Object>> result = new ArrayList<>();
        for (User hostUser : hostUserList) {
            Long houseCount = houseService.countHouseByUserId(hostUser.getId());

            Map<String, Object> hostUserWithHouseCount = new HashMap<>();
            hostUserWithHouseCount.put("user", hostUser);
            hostUserWithHouseCount.put("houseCount", houseCount);

            result.add(hostUserWithHouseCount);
        }

        return result;
    }

    @Override
    public User getHostById(Long id) {
        return userRepository.findByIdAndRole(id, Role.HOST);
    }

    @Override
    public User updateUser(User updatedUser) {
        User currentUser = getCurrentUser();
        if (updatedUser.getFirstName() != null) {
            currentUser.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            currentUser.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmail() != null) {
            currentUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPhoneNumber() != null) {
            currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
        }
        if (updatedUser.getAddress() != null) {
            currentUser.setAddress(updatedUser.getAddress());
        }
        if (updatedUser.getProfileImage() != null) {
            currentUser.setProfileImage(updatedUser.getProfileImage());
        }
        return userRepository.save(currentUser);
    }

    @Override
    public User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUserOptional = userRepository.findByUsername(username);

        if (currentUserOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        return currentUserOptional.get();
    }


}