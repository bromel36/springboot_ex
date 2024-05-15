package com.bromel.exercise.repository;

import com.bromel.exercise.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.school = ?1")
    Page<Student> findBySchool(Pageable pageable);

    @Query("select s from Student s where s.age = ?1")
    Page<Student> findByAge(Pageable pageable);

    @Query("select s from Student s where s.name = ?1")
    Page<Student> findByName(Pageable pageable);

    @Query("select s from Student s where s.name = ?1 and s.school = ?2")
    Page<Student> findByNameAndSchool(Pageable pageable);

    @Query("select s from Student s where s.name = ?1 and s.age = ?2")
    Page<Student> findByNameAndAge(Pageable pageable);
}
