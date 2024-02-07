package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.dto.UserDto;
import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api1")
public class AdminController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/get-all-user-details")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<UserDto>> getAllUserDetails() {
        List<UserDto> userList = customUserDetailsService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/get-user-detail/{username}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserDto> getUserDetail(@PathVariable String username) {
        UserDto userList = customUserDetailsService.getUserDetail(username);
        return ResponseEntity.ok(userList);
    }

}
