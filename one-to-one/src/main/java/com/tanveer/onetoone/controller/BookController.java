package com.tanveer.onetoone.controller;

import com.tanveer.onetoone.model.BookRequest;
import com.tanveer.onetoone.model.BookResponse;
import com.tanveer.onetoone.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/many-to-many/book/")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest){
        BookResponse bookResponse = bookService.createBook(bookRequest);
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }
}
