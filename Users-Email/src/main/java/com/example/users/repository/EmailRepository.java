package com.example.users.repository;

import com.example.users.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<EmailEntity,Long> {

 List<EmailEntity> findByUserEntityName(String name);
 EmailEntity findByEmail(String email);
}
