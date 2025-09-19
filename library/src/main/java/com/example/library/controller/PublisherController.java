package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.entity.Publisher;
import com.example.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherService.createPublisher(publisher));
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        return publisherService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherService.update(id, publisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.ok("Publisher deleted successfully with id: " + id);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getBooksByPublisher(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.findBooksByPublisherId(id));
    }
}
