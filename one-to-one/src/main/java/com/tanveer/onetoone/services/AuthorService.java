package com.tanveer.onetoone.services;

import com.tanveer.onetoone.entity.AuthorEntity;
import com.tanveer.onetoone.entity.BookEntity;
import com.tanveer.onetoone.model.AuthorRequest;
import com.tanveer.onetoone.model.AuthorResponse;
import com.tanveer.onetoone.model.BookRequest;
import com.tanveer.onetoone.repository.AuthorRepository;
import com.tanveer.onetoone.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(authorRequest.getName());
        authorEntity.setId(authorRequest.getId());
        authorRepository.save(authorEntity);

        AuthorResponse response = new AuthorResponse();
        response.setId(authorEntity.getId());
        return response;
    }
}
