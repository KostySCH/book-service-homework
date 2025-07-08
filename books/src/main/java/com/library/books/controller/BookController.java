package com.library.books.controller;

import com.library.books.model.Book;
import com.library.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

    @PostMapping("/{id}/issue")
    public Book issueBook(@PathVariable Long id, @RequestParam Long userId) {
        return bookService.issueBook(id, userId);
    }

    @PostMapping("/{id}/return")
    public Book returnBook(@PathVariable Long id, @RequestParam Long userId) {
        return bookService.returnBook(id, userId);
    }
} 