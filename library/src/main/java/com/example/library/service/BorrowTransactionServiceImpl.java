package com.example.library.service;

import com.example.library.dto.BorrowTransactionDTO;
import com.example.library.entity.Book;
import com.example.library.entity.BorrowTransaction;
import com.example.library.entity.User;
import com.example.library.mapper.BorrowTransactionMapper;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowTransactionRepository;
import com.example.library.repository.UserRepository;
import com.example.library.service.BorrowTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowTransactionServiceImpl implements BorrowTransactionService {

    @Autowired
    private BorrowTransactionRepository borrowRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserActivityService userActivityService;
    @Autowired
    private BorrowTransactionMapper borrowMapper;

    @Override
    public BorrowTransactionDTO borrowBook(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getCurrentCopies() <= 0) {
            throw new RuntimeException("No copies available for this book");
        }

        book.setCurrentCopies(book.getCurrentCopies() - 1);
        bookRepository.save(book);

        BorrowTransaction borrow = new BorrowTransaction();
        borrow.setBook(book);
        borrow.setMember(user);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusWeeks(2));

        BorrowTransaction savedBorrow = borrowRepository.save(borrow);

        userActivityService.logActivity(user, "Borrowed book: " + book.getTitle());

        return borrowMapper.toDto(savedBorrow);
    }


    @Override
    public List<BorrowTransactionDTO> getAllTransactions() {
        return borrowRepository.findAll().stream().map(t->borrowMapper.toDto(t)).collect(Collectors.toList());
    }
}
