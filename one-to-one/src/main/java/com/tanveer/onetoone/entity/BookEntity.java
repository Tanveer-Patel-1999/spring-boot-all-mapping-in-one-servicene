package com.tanveer.onetoone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Books")
@Getter
@Setter
public class BookEntity {

    @Id
    @SequenceGenerator(name = "book_id" , sequenceName = "book_id",initialValue = 100 , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "book_id")
    private Long id;

    @Column(name = "book_title")
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Book_Author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> authors = new ArrayList<>();
}

/*
############ Many-to-Many ################
step 1 :
    BookEntity
    ---------

    @ManyToMany(cascade = CascadeType.ALL) : the annotation is used create a many-to-many relationship
    @JoinTable(
            name = "Book_Author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> authors = new ArrayList<>(); : create an object of another class

--> @joinTable : It is used create new table with both table primary key
--> @joinTable : have 3 things
                1 : name : new table name with both table primary key.
                2 : joinColumns : have two things
                        1 : @JoinColumn(name = "book_id") : first table primary key
                3 : inverseJoinColumns = @JoinColumn(name = "author_id") : second table primary key

step 2 :
    AuthorEntity
    ------------
    @ManyToMany(mappedBy = "authors") : used the instance of one class
    private List<BookEntity> books = new ArrayList<>(); : create an instance of BookEntity


 */
