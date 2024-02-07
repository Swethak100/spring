package com.example.libraryManagement.controller;

import com.example.libraryManagement.dto.BookDto;
import com.example.libraryManagement.dto.CategoryDto;
import com.example.libraryManagement.enums.CategoryType;
import com.example.libraryManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{name}")
    public List<BookDto> getBooksByCategory(@PathVariable String name) {
        return categoryService.getCategoryByName(CategoryType.valueOf(name)).getBooks();
    }
}
