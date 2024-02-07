package com.example.libraryManagement.repository;

import com.example.libraryManagement.model.Book;
import com.example.libraryManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByCategory(Category category);
}
