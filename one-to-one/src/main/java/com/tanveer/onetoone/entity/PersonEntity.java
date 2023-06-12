package com.tanveer.onetoone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Person")
public class PersonEntity {

    @Id
    @SequenceGenerator(name = "person_id", sequenceName = "person_id", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id")
    private Long id;

    @Column(name = "person_first_name")
    private String firstName;

    @Column(name ="person_last_name")
    private String lastName;

    @Column(name = "person_age")
    private Integer age;
}



