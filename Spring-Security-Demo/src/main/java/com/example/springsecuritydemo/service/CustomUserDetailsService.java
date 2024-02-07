package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.dto.UserDto;
import com.example.springsecuritydemo.repository.UserRepository;
import com.example.springsecuritydemo.entity.CustomUserDetails;
import com.example.springsecuritydemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> "user".equals(user.getRole()))
                .map(user -> new UserDto(user.getUsername(), user.getCity()))
                .collect(Collectors.toList());
    }

    public UserDto getUserDetail(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserDto(user.getUsername(), user.getCity());
    }

    public UserDto getMyDetail(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        User u = user.get();
        return new UserDto(u.getUsername(), u.getCity());
    }
}
