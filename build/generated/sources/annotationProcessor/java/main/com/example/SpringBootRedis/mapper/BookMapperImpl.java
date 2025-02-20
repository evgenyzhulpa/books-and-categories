package com.example.SpringBootRedis.mapper;

import com.example.SpringBootRedis.dto.BookResponse;
import com.example.SpringBootRedis.model.Book;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T09:05:36+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.2 (Amazon.com Inc.)"
)
@Component
@Primary
public class BookMapperImpl extends BookMapperDelegate {

    @Autowired
    @Qualifier("delegate")
    private BookMapper delegate;

    @Override
    public BookResponse bookToResponse(Book book)  {
        return delegate.bookToResponse( book );
    }

    @Override
    public List<BookResponse> bookListToBookResponseList(List<Book> books)  {
        return delegate.bookListToBookResponseList( books );
    }
}
