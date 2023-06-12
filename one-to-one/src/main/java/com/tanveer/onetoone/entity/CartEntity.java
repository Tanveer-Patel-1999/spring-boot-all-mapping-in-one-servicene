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

    @OneToMany(mappedBy="cart")
    private List<ItemEntity> items;
}
