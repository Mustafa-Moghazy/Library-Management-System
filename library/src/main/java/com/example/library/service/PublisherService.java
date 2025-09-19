package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    Publisher createPublisher(Publisher publisher);
    List<Publisher> findAll();
    Optional<Publisher> findById(Long id);
    void delete (Long id);
    Publisher update(Long id, Publisher publisher);
    List<Book> findBooksByPublisherId(Long id);
}
