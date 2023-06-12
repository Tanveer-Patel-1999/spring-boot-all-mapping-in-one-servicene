package com.tanveer.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    private String cartName;
    private String cartType;
    private List<ItemRequest> items;
}
