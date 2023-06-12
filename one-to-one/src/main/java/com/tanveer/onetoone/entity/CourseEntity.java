package com.tanveer.onetoone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Courses")
@Getter
@Setter
public class CourseEntity {
    @Id
    @SequenceGenerator(name = "course_id" , sequenceName = "course_id",initialValue = 100 , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "course_id")
    private Long id;

    @Column(name = "courseName")
    private String courseName;
    @Column(name = "course_price")
    private Double price;
    @Column(name = "course_duration")
    private Integer days;

    @OneToOne(mappedBy = "course")
    private StudentEntity student;

}
