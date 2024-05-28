package com.example.SpringBootRedis.mapper;

import com.example.SpringBootRedis.dto.BookListResponse;
import com.example.SpringBootRedis.dto.BookResponse;
import com.example.SpringBootRedis.dto.UpsertBookRequest;
import com.example.SpringBootRedis.model.Book;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(BookMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    Book requestToBook(UpsertBookRequest request);

    @Mapping(source = "bookId", target = "id")
    Book requestToBook(Long bookId, UpsertBookRequest request);

    BookResponse bookToResponse(Book book);

    List<BookResponse> bookListToBookResponseList(List<Book> books);

    default BookListResponse bookListToBookListResponse(List<Book> books) {
        BookListResponse response = new BookListResponse();
        response.setBookResponseList(bookListToBookResponseList(books));
        return response;
    }
}
