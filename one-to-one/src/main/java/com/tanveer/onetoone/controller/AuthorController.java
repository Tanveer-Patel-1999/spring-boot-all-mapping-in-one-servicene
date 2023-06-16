package com.tanveer.onetoone.controller;

import com.tanveer.onetoone.model.AuthorRequest;
import com.tanveer.onetoone.model.AuthorResponse;
import com.tanveer.onetoone.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/many-to-many/author/")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest authorRequest){
        AuthorResponse response = authorService.createAuthor(authorRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
