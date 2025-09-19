package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByIsbn(int isbn);
    List<Book> findAllByAuthors_AuthorNameIgnoreCase(String authorName);
    List<Book> findAllByPublisher_PublisherNameIgnoreCase(String publisherName);
}
