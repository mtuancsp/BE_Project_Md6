package com.example.springboot.service.user;


import com.example.springboot.dto.request.ChangePasswordRequest;
import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends IGeneralService<User> {
    UserDetailsService userDetailsService();

    List<User> getAllHosts();
    User acceptHost(Long id, String message);

    User rejectHost(Long id, String message);
    ResponseEntity<String> applyHost(String username);

    User blockUser(Long id);

    List<User> getUsersWithApplyHost();

    List<Map<String, Object>> getHostUsersWithHouseCount();

    User getHostById(Long id);

    User updateUser(User updatedUser);

    User unlockUser(Long id);

    User getCurrentUser();
}