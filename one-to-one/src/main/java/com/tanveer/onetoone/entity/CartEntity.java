package com.tanveer.onetoone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Carts")
@Getter
@Setter
public class CartEntity {

    @Id
    @SequenceGenerator(name = "cart_id" , sequenceName = "cart_id",initialValue = 100 , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "cart_id")
    private Long id;

    @Column(name = "cart_name")
    private String cartName;
    @Column(name = "cart_type")
    private String cartType;

    @OneToMany(mappedBy="cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemEntity> items;

}

/*
one to many
---------
1 : In parent class
--> used :
     1 : @OneToMany(mappedBy = "parent_object", cascade = CascadeType.ALL)
     2 : private List<ChildEntity> children; : create an object of child class

2 : In child class
--> used :
    1 : @ManyToOne
    2 : @JoinColumn(name = "parent_id")
    3 : private ParentEntity parent;  : create a ParentEntity an object in child class

pojo :

public class ParentDTO {

    private Long id;
    private String name;
    private List<ChildDTO> children;

    // constructors, getters, and setters
}

public class ChildDTO {

    private Long id;
    private String name;

    // constructors, getters, and setters
}

note : both instances name are same in EntityClass and Model class.
 */