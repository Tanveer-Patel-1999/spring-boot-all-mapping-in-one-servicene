package com.tanveer.onetoone.controller;

import com.tanveer.onetoone.model.PersonRequest;
import com.tanveer.onetoone.model.StudentRequest;
import com.tanveer.onetoone.model.StudentResponse;
import com.tanveer.onetoone.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/one-to-one/student/")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.createStudent(studentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRequest> getStudentById(@PathVariable Long id){
        StudentRequest request = studentService.getStudentById(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentRequest>> getAll(){
        List<StudentRequest> requests = studentService.getAllStudent();
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentRequest> updateById(@RequestBody StudentRequest studentRequest, @PathVariable Long id){
        StudentRequest request = studentService.updateById(studentRequest,id);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
