package com.tanveer.onetoone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Items")
@Getter
@Setter
public class ItemEntity {

    @Id
    @SequenceGenerator(name = "item_id" , sequenceName = "item_id",initialValue = 100 , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "item_id")
    private Long id;
    private String itemName;
    private Double price;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private CartEntity cart;
}
