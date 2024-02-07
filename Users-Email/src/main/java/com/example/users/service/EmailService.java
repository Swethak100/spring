package com.example.users.service;

import com.example.users.dto.EmailDto;
import com.example.users.entity.EmailEntity;
import com.example.users.entity.UserEntity;
import com.example.users.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

   public List<EmailEntity> findByUserEntityName(String name){
        return emailRepository.findByUserEntityName(name);
    }

    public void createEmail(EmailDto emailDto, Long userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmail(emailDto.getEmail());
        emailEntity.setUserEntity(userEntity);

        emailRepository.save(emailEntity);
    }

    public UserEntity getUserByEmail(String email) {
        return emailRepository.findByEmail(email).getUserEntity();
    }
}
