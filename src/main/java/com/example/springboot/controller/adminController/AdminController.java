package com.example.springboot.controller.adminController;

import com.example.springboot.model.User;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/list-user")
    public ResponseEntity<Iterable<User>> showAllListUser(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/list-host")
    public ResponseEntity<List<Map<String, Object>>> getHostUsersWithHouseCount() {
        List<Map<String, Object>> hostUserHouseCountList = userService.getHostUsersWithHouseCount();
        return ResponseEntity.ok(hostUserHouseCountList);
    }

    @GetMapping("/apply-host")
    public ResponseEntity<List<User>> showListApplyHost(){
        return new ResponseEntity<>(userService.getUsersWithApplyHost(), HttpStatus.OK);
    }

    @GetMapping("host/{id}")
    public ResponseEntity<User> detailHostById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getHostById(id), HttpStatus.OK);
    }

    @PostMapping("/accept-host/{id}")
    public ResponseEntity<User> acceptHost(@PathVariable Long id, @RequestBody String message){
        User user =  userService.acceptHost(id, message);
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reject-host/{id}")
    public ResponseEntity<User> rejectHost(@PathVariable Long id, @RequestBody String message){
        User user =  userService.rejectHost(id, message);
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/block-user/{id}")
    public ResponseEntity<User> blockUser(@PathVariable Long id){
        User user =  userService.blockUser(id);
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/unlock-user/{id}")
    public ResponseEntity<User> unlockUser(@PathVariable Long id){
        User user =  userService.unlockUser(id);
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

}
