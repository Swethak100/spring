package com.example.libraryManagement.repository;

import com.example.libraryManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.libraryManagement.enums.CategoryType;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(CategoryType name);
}
