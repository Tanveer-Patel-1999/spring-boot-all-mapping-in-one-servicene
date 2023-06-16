package com.tanveer.onetoone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Authors")
@Getter
@Setter
public class AuthorEntity {

    @Id
    @SequenceGenerator(name = "author_id" , sequenceName = "author_id",initialValue = 100 , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "author_id")
    private Long id;

    @Column(name = "author_name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books = new ArrayList<>();

}
