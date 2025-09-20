package com.example.library.service;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.entity.Publisher;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import com.example.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookResponseDTO createBook(BookRequestDTO dto) {

        Optional<Book> existing = bookRepository.findByTitle(dto.getTitle());
        if(existing.isPresent()){
            Book book = existing.get();
            book.setTotalCopies(book.getTotalCopies() + 1);
            bookRepository.save(book);
            return bookMapper.toBookDTO(book);
        }

        Book book = bookMapper.toEntity(dto);

        // create a new one if doesn't exisit //
        Category category = categoryRepository.findByCategoryNameIgnoreCase(dto.getCategoryName())
                .orElseGet(() -> categoryRepository.save(new Category(dto.getCategoryName())));
        book.setCategory(category);

        Publisher publisher = publisherRepository.findByPublisherNameIgnoreCase(dto.getPublisherName())
                .orElseGet(() -> publisherRepository.save( new Publisher(dto.getPublisherName())));
        book.setPublisher(publisher);

        List<Author> authors = dto.getAuthors().stream()
                .map(name -> authorRepository.findByAuthorNameIgnoreCase(name)
                        .orElseGet(() -> authorRepository.save(new Author(name))))
                .collect(Collectors.toList());
        book.setAuthors(authors);

        Book saved = bookRepository.save(book);
        return bookMapper.toBookDTO(saved);
    }

    @Override
    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookResponseDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toBookDTO);
    }

    @Override
    public Optional<BookResponseDTO> findById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toBookDTO);
    }

    @Override
    public Optional<BookResponseDTO> findByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle).map(bookMapper::toBookDTO);
    }

    @Override
    public Optional<BookResponseDTO> findByIsbn(int isbn) {
        return bookRepository.findByIsbn(isbn).map(bookMapper::toBookDTO);
    }

    @Override
    public List<BookResponseDTO> findByPublisherName(String publisherName) {
        return bookRepository.findAllByPublisher_PublisherNameIgnoreCase(publisherName)
                .stream().map(bookMapper::toBookDTO).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> findByAuthorName(String authorName) {
        return bookRepository.findAllByAuthors_AuthorNameIgnoreCase(authorName)
                .stream().map(bookMapper::toBookDTO).collect(Collectors.toList());
    }

    @Override
    public List<Author> findAuthorsByBookTitle(String bookTitle) {
        Book existing = bookRepository.findByTitle(bookTitle)
                .orElseThrow(() -> new RuntimeException("Book not found with title: " + bookTitle));
        return existing.getAuthors();
    }

    @Override
    public void delete(Long id) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        bookRepository.delete(existing);
    }

    @Override
    public BookResponseDTO update(Long id, BookRequestDTO dto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        // update fields from dto //
        existing.setTitle(dto.getTitle());
        existing.setEdition(dto.getEdition());
        existing.setIsbn(dto.getIsbn());
        existing.setPublicationYear(dto.getPublicationYear());
        existing.setCoverImg(dto.getCoverImg());
        existing.setLanguage(dto.getLanguage());
        existing.setSummary(dto.getSummary());
        existing.setTotalCopies(dto.getTotalCopies());
        existing.setCurrentCopies(dto.getCurrentCopies());

        Category category = categoryRepository.findByCategoryNameIgnoreCase(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setCategory(category);

        Publisher publisher = publisherRepository.findByPublisherNameIgnoreCase(dto.getPublisherName())
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
        existing.setPublisher(publisher);

        List<Author> authors = dto.getAuthors().stream()
                .map(name -> authorRepository.findByAuthorNameIgnoreCase(name)
                        .orElseGet(() -> authorRepository.save(new Author(name))))
                .collect(Collectors.toList());
        existing.setAuthors(authors);

        Book updated = bookRepository.save(existing);
        return bookMapper.toBookDTO(updated);
    }
}
