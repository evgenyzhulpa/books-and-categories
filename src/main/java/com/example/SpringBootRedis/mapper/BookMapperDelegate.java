package com.example.SpringBootRedis.mapper;

import com.example.SpringBootRedis.dto.UpsertBookRequest;
import com.example.SpringBootRedis.model.Book;
import com.example.SpringBootRedis.model.BookCategory;

public abstract class BookMapperDelegate implements BookMapper {

    @Override
    public Book requestToBook(UpsertBookRequest request) {
        Book book = new Book();
        book.setName(request.getName());
        book.setAuthor(request.getAuthor());

        BookCategory category = new BookCategory();
        category.setName(request.getCategoryName());
        book.setCategory(category);
        return book;
    }

    @Override
    public Book requestToBook(Long bookId, UpsertBookRequest request) {
        Book book = requestToBook(request);
        book.setId(bookId);
        return book;
    }
}
