package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void delete (Long id);
    Category update(Long id, Category category);
    List<Book> findBooksByCategoryId(Long id);
}
