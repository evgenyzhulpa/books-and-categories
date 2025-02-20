package com.example.SpringBootRedis.mapper;

import com.example.SpringBootRedis.dto.BookResponse;
import com.example.SpringBootRedis.dto.UpsertBookRequest;
import com.example.SpringBootRedis.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T09:05:35+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.2 (Amazon.com Inc.)"
)
@Component
@Qualifier("delegate")
public class BookMapperImpl_ implements BookMapper {

    @Override
    public Book requestToBook(UpsertBookRequest request) {
        if ( request == null ) {
            return null;
        }

        Book book = new Book();

        book.setName( request.getName() );
        book.setAuthor( request.getAuthor() );

        return book;
    }

    @Override
    public Book requestToBook(Long bookId, UpsertBookRequest request) {
        if ( bookId == null && request == null ) {
            return null;
        }

        Book book = new Book();

        if ( request != null ) {
            book.setName( request.getName() );
            book.setAuthor( request.getAuthor() );
        }
        book.setId( bookId );

        return book;
    }

    @Override
    public BookResponse bookToResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponse bookResponse = new BookResponse();

        bookResponse.setId( book.getId() );
        bookResponse.setName( book.getName() );
        bookResponse.setAuthor( book.getAuthor() );

        return bookResponse;
    }

    @Override
    public List<BookResponse> bookListToBookResponseList(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookResponse> list = new ArrayList<BookResponse>( books.size() );
        for ( Book book : books ) {
            list.add( bookToResponse( book ) );
        }

        return list;
    }
}
