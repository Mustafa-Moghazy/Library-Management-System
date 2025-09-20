package com.example.library.repository;

import com.example.library.entity.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Long> {
}
