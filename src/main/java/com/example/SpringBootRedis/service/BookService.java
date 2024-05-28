package com.example.SpringBootRedis.service;

import com.example.SpringBootRedis.model.Book;

import java.util.List;

public interface BookService {
    Book findByNameAndAuthor(String name, String author);

    List<Book> findByCategoryName(String categoryName);

    Book save(Book book);

    Book update(Book book);

    void delete(Long id);
}
