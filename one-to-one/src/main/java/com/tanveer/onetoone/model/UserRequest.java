package com.tanveer.onetoone.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "Invalid Username")
    private String name;
    @Email(message = "Invalid email address ")
    private String email;
    @Pattern(regexp = "^//d{10}$" , message = "invalid mobile number")
    private String mobile;

    @NotNull(message = "Please provides a gender")
    private String gender;

    @Min(value = 18,message = "minimum age is 18 to used that application")
    @Max(value = 50,message = "maximum age is 50 to used that application")
    private Integer age;

    @NotBlank(message = "please provides your nationality")
    private String nationality;
}
