package com.bromel.exercise.repository;

import com.bromel.exercise.entity.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    List<Student> findByName(String name);
    List<Student> findByAge(int age);
    List<Student> findByAddress(String address);

    boolean insert(Student student);
    boolean update(Student student);
    boolean delete(Student student);
}
