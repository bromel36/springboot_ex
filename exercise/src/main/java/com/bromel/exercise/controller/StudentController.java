package com.bromel.exercise.controller;

import com.bromel.exercise.dto.request.StudentRequest;
import com.bromel.exercise.entity.Student;
import com.bromel.exercise.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Student> insertStudent(@RequestBody StudentRequest request) {
        return studentService.insertStudent(request);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Integer id, @RequestBody StudentRequest request) {
        return studentService.updateStudent(id, request);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id") Integer id) {
        System.out.println("here");
        return studentService.deleteStudent(id);
    }

    @GetMapping("/details/{id}")
    public Optional<Student> detailsStudent(@PathVariable(value = "id") Integer id) {
        return studentService.getDetailsStudent(id);
    }

    @GetMapping("get-page")
    public ResponseEntity<Page<Student>> getPageAndSearch(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "school", required = false) String school,
            Pageable pageable) {
        Page<Student> students = studentService.getPageAndSearch(name, age, school, pageable);
        return new ResponseEntity<>(students, HttpStatus.OK);

    }
}
