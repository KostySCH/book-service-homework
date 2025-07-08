package com.library.books.service;

import com.library.books.client.UserClient;
import com.library.books.dto.UserDto;
import com.library.books.model.Book;
import com.library.books.model.BookStatus;
import com.library.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserClient userClient;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book issueBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new RuntimeException("Книга уже выдана");
        }

        UserDto user = userClient.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }
        book.setStatus(BookStatus.TAKEN);
        book.setUserId(userId);
        return bookRepository.save(book);
    }

    @Transactional
    public Book returnBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
        if (book.getStatus() != BookStatus.TAKEN || !userId.equals(book.getUserId())) {
            throw new RuntimeException("Книга не выдана этому пользователю");
        }
        book.setStatus(BookStatus.AVAILABLE);
        book.setUserId(null);
        return bookRepository.save(book);
    }
} 