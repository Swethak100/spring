package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.config.JwtTokenUtil;
import com.example.springsecuritydemo.dto.UserTokenDto;
import com.example.springsecuritydemo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api3")
public class TokenController {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @GetMapping("/generate-token")
    public String generateJwtToken(@RequestBody UserTokenDto userTokenDto) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userTokenDto.getUsername(), userTokenDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userTokenDto.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }
}
