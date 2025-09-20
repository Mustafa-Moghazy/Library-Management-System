package com.example.library.service;

import com.example.library.dto.BorrowTransactionDTO;
import com.example.library.entity.BorrowTransaction;
import java.util.List;

public interface BorrowTransactionService {
    BorrowTransactionDTO borrowBook(Long userId, Long bookId);
    List<BorrowTransactionDTO> getAllTransactions();
}
