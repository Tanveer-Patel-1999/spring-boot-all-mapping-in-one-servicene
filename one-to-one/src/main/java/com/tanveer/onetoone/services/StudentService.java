package com.tanveer.onetoone.services;

import com.tanveer.onetoone.entity.CourseEntity;
import com.tanveer.onetoone.entity.StudentEntity;
import com.tanveer.onetoone.model.CourseRequest;
import com.tanveer.onetoone.model.StudentRequest;
import com.tanveer.onetoone.model.StudentResponse;
import com.tanveer.onetoone.repository.CourseRepository;
import com.tanveer.onetoone.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public StudentResponse createStudent(StudentRequest studentRequest) {

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUsn(studentRequest.getUsn());
        studentEntity.setFirstName(studentRequest.getFirstName());
        studentEntity.setLastName(studentRequest.getLastName());
        studentEntity.setEmail(studentRequest.getEmail().replace("\n", "\\n"));

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(studentRequest.getCourse().getCourseName());
        courseEntity.setDays(studentRequest.getCourse().getDays());
        courseEntity.setPrice(studentRequest.getCourse().getPrice());
        studentEntity.setCourse(courseEntity);

//        courseRepository.save(courseEntity);
        studentRepository.save(studentEntity);

        StudentResponse response = new StudentResponse();
        response.setUsn(studentEntity.getUsn());
        return response;
    }

    public StudentRequest getStudentById(Long id) {
        StudentRequest request = new StudentRequest();
        Optional<StudentEntity> entityOptional = studentRepository.findById(id);
        if (entityOptional.isPresent()){

            request.setUsn(entityOptional.get().getUsn());
            request.setFirstName(entityOptional.get().getFirstName());
            request.setLastName(entityOptional.get().getLastName());
            request.setEmail(entityOptional.get().getEmail());

            CourseRequest courseRequest = new CourseRequest();
            courseRequest.setCourseName(entityOptional.get().getCourse().getCourseName());
            courseRequest.setDays(entityOptional.get().getCourse().getDays());
            courseRequest.setPrice(entityOptional.get().getCourse().getPrice());
            request.setCourse(courseRequest);

        }
        return request;
    }

    public List<StudentRequest> getAllStudent() {
        return studentRepository.findAll().stream()
                .map(entity -> {
                    StudentRequest request = new StudentRequest();
                    request.setUsn(entity.getUsn());
                    request.setFirstName(entity.getFirstName());
                    request.setLastName(entity.getLastName());
                    request.setEmail(entity.getEmail());

                    CourseRequest courseRequest = new CourseRequest();
                    courseRequest.setCourseName(entity.getCourse().getCourseName());
                    courseRequest.setDays(entity.getCourse().getDays());
                    courseRequest.setPrice(entity.getCourse().getPrice());
                    request.setCourse(courseRequest);

                    return request;

                }).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        if (optionalStudentEntity.isPresent()){
            studentRepository.deleteById(id);
        }
    }

    public StudentRequest updateById(StudentRequest studentRequest, Long id) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        if (optionalStudentEntity.isPresent()){
            StudentEntity studentEntity = optionalStudentEntity.get();
            studentEntity.setUsn(studentRequest.getUsn());
            studentEntity.setFirstName(studentRequest.getFirstName());
            studentEntity.setLastName(studentRequest.getLastName());
            studentEntity.setEmail(studentRequest.getEmail());

            CourseEntity courseEntity = optionalStudentEntity.get().getCourse();
            courseEntity.setCourseName(studentRequest.getCourse().getCourseName());
            courseEntity.setDays(studentRequest.getCourse().getDays());
            courseEntity.setPrice(studentRequest.getCourse().getPrice());

            studentEntity.setCourse(courseEntity);
//            courseRepository.save(courseEntity);
            studentRepository.save(studentEntity);

        }
        return studentRequest;
    }
}
