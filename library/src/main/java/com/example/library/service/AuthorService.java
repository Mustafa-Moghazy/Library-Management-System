package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author createAuthor(Author author);
    List<Author> findAll();
    Optional<Author> findById(Long id);
    void delete (Long id);
    Author update(Long id, Author author);
    List<Book> findBooksByAuthorId(Long id);
}
