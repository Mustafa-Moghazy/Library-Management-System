package com.example.library.controller;

import com.example.library.dto.BorrowTransactionDTO;
import com.example.library.entity.BorrowTransaction;
import com.example.library.service.BorrowTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowTransactionService borrowService;
    @PostMapping
    public ResponseEntity<BorrowTransactionDTO> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {

        BorrowTransactionDTO borrow = borrowService.borrowBook(userId, bookId);
        return ResponseEntity.ok(borrow);
    }

    @GetMapping
    public ResponseEntity<List<BorrowTransactionDTO>> getAllBorrowTransaction(){
        return ResponseEntity.ok(borrowService.getAllTransactions());
    }
}
