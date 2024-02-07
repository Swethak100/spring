package com.example.libraryManagement.service;

import com.example.libraryManagement.dto.BookDto;
import com.example.libraryManagement.dto.CategoryDto;
import com.example.libraryManagement.enums.CategoryType;
import com.example.libraryManagement.model.Book;
import com.example.libraryManagement.model.Category;
import com.example.libraryManagement.repository.BookRepository;
import com.example.libraryManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<BookDto> showBooks() {
        List<Book> books = bookRepository.findAll();
        return convertToDTOList(books);
    }
    public String buyBooks(List<Long> bookIds) {
        for (Long bookId : bookIds) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            if (book.getStockQuantity() > 0) {
                book.setStockQuantity(book.getStockQuantity() - 1);
                bookRepository.save(book);
            } else {
                throw new RuntimeException("Book out of stock");
            }
        }
        return "Books bought successfully";
    }

    public String addBooks(List<BookDto> bookDTOs) {
        List<Book> books = bookDTOs.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        bookRepository.saveAll(books);
        return "Books added successfully";
    }

    public String updateStockQuantity(Long bookId, int newStockQuantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setStockQuantity(newStockQuantity);
        bookRepository.save(book);
        return "Stock quantity updated successfully";
    }

    public List<BookDto> getBooksByCategory(CategoryType categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        List<Book> books = bookRepository.findByCategory(category);
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public BookDto convertToDTO(Book book) {
        BookDto bookDTO = new BookDto();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setStockQuantity(book.getStockQuantity());

        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setId(book.getCategory().getId());
        categoryDTO.setName(book.getCategory().getName().name());

        bookDTO.setCategory(categoryDTO);

        return bookDTO;
    }

    public Book mapToEntity(BookDto bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setStockQuantity(bookDTO.getStockQuantity());

        Category category = new Category();
        category.setId(bookDTO.getCategory().getId());
        category.setName(CategoryType.valueOf(bookDTO.getCategory().getName()));

        book.setCategory(category);

        return book;
    }
    private List<BookDto> convertToDTOList(List<Book> books) {
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

/*    public List<BookDto> getBooksByCategory(CategoryType categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        List<Book> books = bookRepository.findByCategory(category);
        return books.stream()
                .map(book -> convertToDTO(book))
                .collect(Collectors.toList());
    }

    private BookDto convertToDTO(Book book) {
        BookDto bookDTO = new BookDto();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setStockQuantity(book.getStockQuantity());

        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setId(book.getCategory().getId());
        categoryDTO.setName(book.getCategory().getName().name());

        bookDTO.setCategory(categoryDTO);

        return bookDTO;
    }

    public Book convertToEntity(BookDto bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setStockQuantity(bookDTO.getStockQuantity());

        Category category = new Category();
        category.setId(bookDTO.getCategory().getId());
        category.setName(CategoryType.valueOf(bookDTO.getCategory().getName()));

        book.setCategory(category);

        return book;
    }
    public void buyBooks(List<Long> bookIds) {
        for (Long bookId : bookIds) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            if (book.getStockQuantity() > 0) {
                book.setStockQuantity(book.getStockQuantity() - 1);
                bookRepository.save(book);
            } else {
                throw new RuntimeException("Book out of stock");
            }
        }
    }

    public void updateStockQuantity(Long bookId, int newStockQuantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setStockQuantity(newStockQuantity);
        bookRepository.save(book);
    }
    public void addBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }*/
}
