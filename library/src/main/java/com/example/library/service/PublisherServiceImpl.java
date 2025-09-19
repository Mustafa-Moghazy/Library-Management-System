package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Publisher;
import com.example.library.repository.BookRepository;
import com.example.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));
        publisherRepository.delete(existing);
    }

    @Override
    public Publisher update(Long id, Publisher publisher) {
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));
        existing.setPublisherName(publisher.getPublisherName());
        return publisherRepository.save(existing);
    }

    @Override
    public List<Book> findBooksByPublisherId(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));
        return publisher.getBooks();
    }
}
