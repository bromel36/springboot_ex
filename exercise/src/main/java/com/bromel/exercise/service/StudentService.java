package com.bromel.exercise.service;

import com.bromel.exercise.dto.request.StudentRequest;
import com.bromel.exercise.entity.Student;
import com.bromel.exercise.repository.StudentRepository;
import com.bromel.exercise.validate.StudentValidate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentValidate studentValidate;

    public StudentService(StudentRepository studentRepository, StudentValidate studentValidate) {
        this.studentRepository = studentRepository;
        this.studentValidate = studentValidate;
    }

    @Transactional
    public ResponseEntity<Student> insertStudent(StudentRequest request) {
        studentValidate.checkStudent(request);

        Student student = new Student();

        student.setAge(request.getAge());
        student.setName(request.getName());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setSchool(request.getSchool());
        student.setAddress(request.getAddress());
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @Transactional
    public ResponseEntity<Student> updateStudent(Integer id, StudentRequest request) {
        studentValidate.checkStudent(request);
        var student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setAge(request.getAge());
            student.setName(request.getName());
            student.setPhoneNumber(request.getPhoneNumber());
            student.setSchool(request.getSchool());
            student.setAddress(request.getAddress());
            studentRepository.saveAndFlush(student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Transactional
    public ResponseEntity<String> deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok("Xoa sinh vien id: " + id + " thanh cong");
    }


    public Optional<Student> getDetailsStudent(Integer id) {
        return studentRepository.findById(id);
    }

    public Page<Student> getPageAndSearch(String name, Integer age, String school, Pageable pageable) {
        if (name == null || name.isBlank()) {
            if (school == null && age == null) {
                return studentRepository.findAll(pageable);
            } else if (school != null && age == null) {
                return studentRepository.findBySchool(pageable);
            } else {
                return studentRepository.findByAge(pageable);
            }
        } else {
            if (school == null && age == null) {
                return studentRepository.findByName(pageable);
            } else if (school != null && age == null) {
                return studentRepository.findByNameAndSchool(pageable);
            } else {
                return studentRepository.findByNameAndAge(pageable);
            }
        }
    }
}
