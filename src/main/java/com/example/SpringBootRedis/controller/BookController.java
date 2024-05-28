package com.example.SpringBootRedis.controller;

import com.example.SpringBootRedis.dto.BookListResponse;
import com.example.SpringBootRedis.dto.BookResponse;
import com.example.SpringBootRedis.dto.UpsertBookRequest;
import com.example.SpringBootRedis.mapper.BookMapper;
import com.example.SpringBootRedis.model.Book;
import com.example.SpringBootRedis.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/search")
    public ResponseEntity<BookResponse> findByNameAndAuthor(@RequestParam("name") String name,
                                                            @RequestParam("author") String author) {
        return ResponseEntity.ok(bookMapper.bookToResponse(
                bookService.findByNameAndAuthor(name, author)
        ));
    }

    @GetMapping("/")
    public ResponseEntity<BookListResponse> findByCategory(@RequestParam("categoryName") String categoryName) {
        return ResponseEntity.ok(bookMapper.bookListToBookListResponse(
                bookService.findByCategoryName(categoryName)
        ));
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody UpsertBookRequest request) {
        Book newBook = bookService.save(bookMapper.requestToBook(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookMapper.bookToResponse(newBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable("id") Long bookId,
                                               @RequestBody UpsertBookRequest request) {
        Book updatedBook = bookService.update(bookMapper.requestToBook(bookId, request));
        return ResponseEntity.ok(bookMapper.bookToResponse(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.noContent().build();
    }
}
