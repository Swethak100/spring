package com.example.libraryManagement.dto;

import java.util.List;

public class CategoryDto {

    private Long id;
    private String name;
    public CategoryDto()
    {

    }
    private List<BookDto> books;

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
