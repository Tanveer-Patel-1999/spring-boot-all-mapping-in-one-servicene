package com.tanveer.onetoone.entity;

import com.tanveer.onetoone.model.CourseRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Student")
@Getter
@Setter
public class StudentEntity {
        @Id
        @SequenceGenerator(name = "student_id" , sequenceName = "student_id",initialValue = 1000 , allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "student_id")
        private Long id;

        @Column(name = "Std_usn")
        private String usn;
        @Column(name = "std_first_name")
        private String firstName;
        @Column(name = "std_last_name")
        private String lastName;
        @Column(name = "std_email")
        private String email;
        @OneToOne
        @JoinColumn(name = "course_id")
        private CourseEntity course;

    }

    /*

    step :  1
    =========
    --> StudentEntity is parent class
    --> CourseEntity is child clas
    1 : then u can use child class primary key as foreign key in the child class.

   used :
   1 :  @OneToOne
   2 :  @JoinColumn(name = "course_id") : used primary key of child classEntity
   3:  private ChildClassEntity childClassEntity; : create an object of childEntity

    step : 2
    ========
    --> CourseEntity is a child class

    used :
    1 : used @OneToOne(mappedBy = "childClassEntity") : mappedBy : used child classEntity object in
    @OneToOne(mappedBy = "course")
    private StudentEntity student; create an object of Parent class in the child class.

     */

