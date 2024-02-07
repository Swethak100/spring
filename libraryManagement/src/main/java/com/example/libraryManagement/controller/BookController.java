package com.example.libraryManagement.controller;

import com.example.libraryManagement.dto.BookDto;
import com.example.libraryManagement.enums.CategoryType;
import com.example.libraryManagement.model.Book;
import com.example.libraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/showAllBooks")
    public ResponseEntity<List<BookDto>> showBooks() {
        List<BookDto> bookDTOs = bookService.showBooks();
        return ResponseEntity.ok(bookDTOs);
    }
    @PostMapping("/buyBooks")
    public ResponseEntity<String> buyBooks(@RequestBody List<Long> bookIds) {
        return ResponseEntity.ok(bookService.buyBooks(bookIds));
    }

    @PostMapping("/addBooks")
    public ResponseEntity<String> addBooks(@RequestBody List<BookDto> bookDTOs) {
        return ResponseEntity.ok(bookService.addBooks(bookDTOs));
    }

    @PostMapping("/updateStock/{bookId}")
    public ResponseEntity<String> updateStockQuantity(@PathVariable Long bookId, @RequestParam int newStockQuantity) {
        return ResponseEntity.ok(bookService.updateStockQuantity(bookId, newStockQuantity));
    }

   /* @PostMapping("/buy")
    public ResponseEntity<String> buyBooks(@RequestBody List<Long> bookIds) {
        try {
            bookService.buyBooks(bookIds);
            return ResponseEntity.ok("Books bought successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBooks(@RequestBody List<BookDto> bookDTOs) {
        List<Book> books = bookDTOs.stream()
                .map(dto -> bookService.convertToEntity(dto))
                .collect(Collectors.toList());

        bookService.addBooks(books);
        return ResponseEntity.ok("Books added successfully");
    }


    @PostMapping("/updateStock/{bookId}")
    public ResponseEntity<String> updateStockQuantity(@PathVariable Long bookId, @RequestParam int newStockQuantity) {
        try {
            bookService.updateStockQuantity(bookId, newStockQuantity);
            return ResponseEntity.ok("Stock quantity updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

}
