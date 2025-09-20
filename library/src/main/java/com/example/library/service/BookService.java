package com.example.library.service;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO book);
    List<BookResponseDTO> findAll();
    Page<BookResponseDTO> findAll(Pageable pageable);
    Optional<BookResponseDTO> findById(Long id);
    Optional<BookResponseDTO> findByTitle(String bookTitle);
    Optional<BookResponseDTO> findByIsbn(int isbn);
    List<BookResponseDTO> findByPublisherName(String publisherName);
    List<BookResponseDTO> findByAuthorName(String authorName);
    List<Author> findAuthorsByBookTitle(String bookTitle);
    void delete (Long id);
    BookResponseDTO update (Long id, BookRequestDTO book);
}
