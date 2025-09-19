package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Book>> getBooksPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        return bookService.findByTitle(title)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Book not found with title: " + title));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable int isbn) {
        return bookService.findByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
    }

    @GetMapping("/publisher/{publisherName}")
    public ResponseEntity<List<Book>> getBooksByPublisher(@PathVariable String publisherName) {
        return ResponseEntity.ok(bookService.findByPublisherName(publisherName));
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String authorName) {
        return ResponseEntity.ok(bookService.findByAuthorName(authorName));
    }

    @GetMapping("/authors/{bookTitle}")
    public ResponseEntity<List<Author>> getAuthorsOfBook(@PathVariable String bookTitle) {
        return ResponseEntity.ok(bookService.findAuthorsByBookTitle(bookTitle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Book deleted successfully with id: " + id);
    }
}
