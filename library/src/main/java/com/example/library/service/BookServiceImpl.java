package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @Override
    public Optional<Book> findByIsbn(int isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> findByPublisherName(String publisherName) {
        return bookRepository.findAllByPublisher_PublisherNameIgnoreCase(publisherName);
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return bookRepository.findAllByAuthors_AuthorNameIgnoreCase(authorName);
    }

    @Override
    public List<Author> findAuthorsByBookTitle(String bookTitle) {
        Book existing = bookRepository.findByTitle(bookTitle).orElseThrow(()-> new RuntimeException("Book not found with title: " + bookTitle ));
        return existing.getAuthors();
    }

    @Override
    public void delete(Long id) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        bookRepository.delete(existing);
    }

    @Override
    public Book update(Long id, Book book) {
        Book existing = findById(id).orElseThrow(()-> new RuntimeException("Book not found with id: " + id));
        existing.setTitle(book.getTitle());
        existing.setEdition(book.getEdition());
        existing.setIsbn(book.getIsbn());
        existing.setPublicationYear(book.getPublicationYear());
        existing.setCoverImg(book.getCoverImg());
        existing.setLanguage(book.getLanguage());
        existing.setSummary(book.getSummary());
        existing.setTotalCopies(book.getTotalCopies());
        existing.setCurrentCopies(book.getCurrentCopies());
        existing.setAuthors(book.getAuthors());
        existing.setCategory(book.getCategory());
        existing.setPublisher(book.getPublisher());
        return bookRepository.save(existing);
    }

}
