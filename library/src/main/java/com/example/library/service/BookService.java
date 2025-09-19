package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book createBook(Book book);
    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String bookTitle);
    Optional<Book> findByIsbn(int isbn);
    List<Book> findByPublisherName(String publisherName);
    List<Book> findByAuthorName(String authorName);
    List<Author> findAuthorsByBookTitle(String bookTitle);
    void delete (Long id);
    Book update (Long id, Book book);
}
