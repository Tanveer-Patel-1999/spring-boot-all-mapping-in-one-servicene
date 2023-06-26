package com.tanveer.onetoone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private Integer age;
    private String nationality;

}
