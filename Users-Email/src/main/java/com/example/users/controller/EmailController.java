package com.example.users.controller;

import com.example.users.dto.EmailDto;
import com.example.users.entity.EmailEntity;
import com.example.users.entity.UserEntity;
import com.example.users.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @GetMapping("/get_name")
    List<EmailEntity> getNames(@RequestParam String name)
    {
        return emailService.findByUserEntityName(name);
    }

    @PostMapping("/create_email")
    public ResponseEntity<String> createEmail(@RequestBody EmailDto emailDto, @RequestParam Long userId) {
        emailService.createEmail(emailDto, userId);
        return new ResponseEntity<>("Email created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get_user_by_email")
    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam String email) {
        UserEntity userEntity = emailService.getUserByEmail(email);
            return ResponseEntity.ok(userEntity);
    }
}
