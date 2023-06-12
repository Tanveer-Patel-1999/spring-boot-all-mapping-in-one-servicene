package com.tanveer.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest extends ItemResponse {
    private String itemName;
    private Double price;
}
