package com.bromel.exercise;

import com.bromel.exercise.entity.Student;
import com.bromel.exercise.repository.IStudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ExerciseApplicationTests {

	@Autowired
	private IStudentRepository studentRepository;

	@Test
	void contextLoads() {
		Student s = new Student();
		s.setName("Nguyen Minh Thanh");
		s.setAge(14);
		s.setAddress("Ho Chi Minh");
		s.setSchool("UTC2");
		s.setPhoneNumber("0981123456");

		List<Student> list ;
		System.out.println("All student");
		list = studentRepository.findAll();
		if(list.size() > 0){
			for(Student x : list){
				System.out.println(x);
			}
		}
		s.setId(list.get(list.size()-1).getId());
		System.out.println("Delete result:"+ studentRepository.delete(s));
		list = studentRepository.findAll();
		if(list.size() > 0){
			for(Student x : list){
				System.out.println(x);
			}
		}
	}

}
