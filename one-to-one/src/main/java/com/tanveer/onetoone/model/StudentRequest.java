package com.tanveer.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest  {

    private String usn;

    private String firstName;
    private String lastName;
    private String email;
    private CourseRequest course;
}
