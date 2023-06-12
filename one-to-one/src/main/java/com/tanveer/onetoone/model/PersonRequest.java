package com.tanveer.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private String firstName;
    private String lastName;
    private Integer age;

}
