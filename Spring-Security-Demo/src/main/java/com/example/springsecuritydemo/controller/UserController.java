package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.config.JwtTokenUtil;
import com.example.springsecuritydemo.dto.UserDto;
import com.example.springsecuritydemo.dto.UserTokenDto;
import com.example.springsecuritydemo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api2")
public class UserController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/view-user-information/{id}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<UserDto> viewMyDetail(@PathVariable Long id)
    {
        UserDto userdetail = customUserDetailsService.getMyDetail(id);
        return ResponseEntity.ok(userdetail);
    }

}
