package com.example.libraryManagement.service;

import com.example.libraryManagement.dto.CategoryDto;
import com.example.libraryManagement.enums.CategoryType;
import com.example.libraryManagement.model.Category;
import com.example.libraryManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> convertToDTO(category))
                .collect(Collectors.toList());
    }

    public CategoryDto getCategoryByName(CategoryType name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return convertToDTO(category);
    }

    private CategoryDto convertToDTO(Category category) {
        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName().name());
        return categoryDTO;
    }
}
