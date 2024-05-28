package com.example.SpringBootRedis.service.impl;

import com.example.SpringBootRedis.configuration.properties.AppCacheProperties;
import com.example.SpringBootRedis.exception.EntityNotFoundException;
import com.example.SpringBootRedis.model.Book;
import com.example.SpringBootRedis.model.BookCategory;
import com.example.SpringBootRedis.repository.BookCategoryRepository;
import com.example.SpringBootRedis.repository.BookRepository;
import com.example.SpringBootRedis.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final String cacheNameBookEntityByNameAndAuthor = AppCacheProperties.CacheNames.BOOK_ENTITY_BY_NAME_AND_AUTHOR;
    private final String cacheNameBookEntityByCategoryName = AppCacheProperties.CacheNames.BOOK_ENTITIES_BY_CATEGORY_NAME;

    @Cacheable(cacheNames = cacheNameBookEntityByNameAndAuthor,
            key = "#name + #author")
    @Override
    public Book findByNameAndAuthor(String name, String author) {
        return bookRepository.findByNameAndAuthor(name, author)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("The book with name {0} and author {1} did not found!", name, author)
                ));
    }

    @Cacheable(cacheNames = cacheNameBookEntityByCategoryName,
            key = "#categoryName")
    @Override
    public List<Book> findByCategoryName(String categoryName) {
        Optional<BookCategory> categoryOptional = bookCategoryRepository.findByName(categoryName);
        if (categoryOptional.isPresent()) {
            return bookRepository.findByCategoryId(categoryOptional.get().getId());
        }
        return new ArrayList<>();
    }

    @Caching(evict = {
            @CacheEvict(value = cacheNameBookEntityByNameAndAuthor,
                    key = "#book.name + #book.author",
                    beforeInvocation = true),
            @CacheEvict(value = cacheNameBookEntityByCategoryName,
                    key = "#book.category.name",
                    beforeInvocation = true)
    })
    @Override
    public Book save(Book book) {
        BookCategory bookCategory = saveBookCategory(book);
        return bookRepository.save(book);
    }

    private BookCategory saveBookCategory(Book book) {
        String categoryName = book.getCategory().getName();
        Optional<BookCategory> categoryOptional = bookCategoryRepository.findByName(categoryName);
        BookCategory category = categoryOptional.isPresent() ? categoryOptional.get() : new BookCategory();
        category.setName(categoryName);
        book.setCategory(category);
        return bookCategoryRepository.save(category);
    }

    @Caching(evict = {
            @CacheEvict(value = cacheNameBookEntityByNameAndAuthor,
                    key = "#book.name + #book.author",
                    beforeInvocation = true),
            @CacheEvict(value = cacheNameBookEntityByCategoryName,
                    key = "#book.category.name",
                    beforeInvocation = true)
    })
    @Override
    public Book update(Book book) {
        Long id = book.getId();
        Book existedBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("The book with id {0} did not found", id)
                ));
        existedBook.setName(book.getName());
        existedBook.setAuthor(book.getAuthor());
        existedBook.setCategory(saveBookCategory(book));

        return bookRepository.save(existedBook);
    }

    @Caching(evict = {
            @CacheEvict(value = cacheNameBookEntityByNameAndAuthor,
                    allEntries = true,
                    beforeInvocation = true),
            @CacheEvict(value = cacheNameBookEntityByCategoryName,
                    allEntries = true,
                    beforeInvocation = true)
    })
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
