package com.bromel.exercise.repository.impl;

import com.bromel.exercise.entity.Student;
import com.bromel.exercise.repository.IStudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
@Transactional
public class StudentRepository implements IStudentRepository {

    @PersistenceContext
    private  EntityManager entityManager;


    @Override
    public List<Student> findAll() {
        String jpql = "Select s from Student s";
        TypedQuery<Student> query = entityManager.createQuery(jpql,Student.class);
        List<Student> studentList = query.getResultList();
        return studentList;
    }

    @Override
    public List<Student> findByName(String name) {
        String jpql = "Select s from Student s where s.name =:name ";
        TypedQuery<Student> query = entityManager.createQuery(jpql,Student.class);
        query.setParameter("name", name);
        List<Student> studentList = query.getResultList();
        return studentList;
    }

    @Override
    public List<Student> findByAge(int age) {
        String jpql = "Select s from Student s where s.age =:age ";
        TypedQuery<Student> query = entityManager.createQuery(jpql,Student.class);
        query.setParameter("age", age);
        List<Student> studentList = query.getResultList();
        return studentList;
    }

    @Override
    public List<Student> findByAddress(String address) {
        String jpql = "Select s from Student s where s.address =:address ";
        TypedQuery<Student> query = entityManager.createQuery(jpql,Student.class);
        query.setParameter("address", address);
        List<Student> studentList = query.getResultList();
        return studentList;
    }

    @Override
    public boolean insert(Student student) {
        String jpql = "INSERT INTO Student(name, age, address,phoneNumber,school)" +
                "Values(:name, :age, :address, :phoneNumber, :school)";
        Query query = entityManager.createNativeQuery(jpql);
        query.setParameter("name", student.getName());
        query.setParameter("age", student.getAge());
        query.setParameter("address", student.getAddress());
        query.setParameter("phoneNumber", student.getPhoneNumber());
        query.setParameter("school", student.getSchool());
        return query.executeUpdate()>0?true:false;
    }

    @Override
    public boolean update(Student student) {
        String jpql = "UPDATE Student " +
                "SET name=:name,age=:age,address= :address, phoneNumber=:phoneNumber, school=:school" +
                " WHERE id=:id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("name", student.getName());
        query.setParameter("age", student.getAge());
        query.setParameter("address", student.getAddress());
        query.setParameter("phoneNumber", student.getPhoneNumber());
        query.setParameter("school", student.getSchool());
        query.setParameter("id",student.getId());
        return query.executeUpdate()>0?true:false;
    }

    @Override
    public boolean delete(Student student) {
        String jpql = "DELETE FROM Student WHERE id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id",student.getId());
        return query.executeUpdate()>0?true:false;
    }
}
