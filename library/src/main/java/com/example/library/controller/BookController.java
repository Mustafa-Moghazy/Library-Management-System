package com.example.library.controller;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.entity.Author;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<BookResponseDTO>> getBooksPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookResponseDTO>> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Optional<BookResponseDTO>> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Optional<BookResponseDTO>> getBookByIsbn(@PathVariable int isbn) {
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @GetMapping("/publisher/{publisherName}")
    public ResponseEntity<List<BookResponseDTO>> getBooksByPublisher(@PathVariable String publisherName) {
        return ResponseEntity.ok(bookService.findByPublisherName(publisherName));
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthor(@PathVariable String authorName) {
        return ResponseEntity.ok(bookService.findByAuthorName(authorName));
    }

    @GetMapping("/authors/{bookTitle}")
    public ResponseEntity<List<Author>> getAuthorsOfBook(@PathVariable String bookTitle) {
        return ResponseEntity.ok(bookService.findAuthorsByBookTitle(bookTitle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO book) {
        return ResponseEntity.ok(bookService.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Book deleted successfully with id: " + id);
    }
}
