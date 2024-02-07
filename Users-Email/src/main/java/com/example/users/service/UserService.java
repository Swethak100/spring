package com.example.users.service;

import com.example.users.dto.UserDto;
import com.example.users.entity.EmailEntity;
import com.example.users.entity.UserEntity;
import com.example.users.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> createUser(UserDto userDto) {
        try {
            UserEntity user = new UserEntity();
            user.setName(userDto.getName());

            List<EmailEntity> emailList = userDto.getEmails().stream()
                    .map(emailStr -> {
                        EmailEntity email = new EmailEntity();
                        email.setEmail(emailStr);
                        return email;
                    })
                    .collect(Collectors.toList());

         //   user.setEmails(emailList);
            userRepository.save(user);

            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

}
