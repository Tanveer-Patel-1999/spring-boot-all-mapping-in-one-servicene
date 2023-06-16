package com.tanveer.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest extends BookResponse {
    private String title;
    private List<AuthorRequest> authors = new ArrayList<>();

}
