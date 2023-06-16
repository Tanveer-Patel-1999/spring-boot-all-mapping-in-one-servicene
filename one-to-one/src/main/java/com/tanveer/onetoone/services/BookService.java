package com.tanveer.onetoone.services;

import com.tanveer.onetoone.entity.AuthorEntity;
import com.tanveer.onetoone.entity.BookEntity;
import com.tanveer.onetoone.model.AuthorRequest;
import com.tanveer.onetoone.model.BookRequest;
import com.tanveer.onetoone.model.BookResponse;
import com.tanveer.onetoone.repository.AuthorRepository;
import com.tanveer.onetoone.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookResponse createBook(BookRequest bookRequest) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookRequest.getTitle());
        bookRepository.save(bookEntity);

        List<AuthorEntity> authorEntities = new ArrayList<>();
        for (AuthorRequest authorRequest : bookRequest.getAuthors()){
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setId(authorRequest.getId());
            authorEntity.setName(authorRequest.getName());
            authorRepository.save(authorEntity);
            authorEntities.add(authorEntity);
        }
        bookEntity.setAuthors(authorEntities);

        BookResponse response = new BookResponse();
        response.setId(bookEntity.getId());
        return response;
    }
}
