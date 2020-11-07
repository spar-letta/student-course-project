package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.model.Course;
import com.example.model.Student;
import com.example.service.StudentService;

@SpringBootApplication
public class StudentProjectApplication {


	Logger logger = LoggerFactory.getLogger(StudentProjectApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(StudentProjectApplication.class, args);
	}
	
	@Autowired
	private StudentService studentService;
	
	@Bean
	public CommandLineRunner dbData() {
		return args ->{
			List<Student> list_students = new ArrayList<>();
			
			Course course1 = new Course("Programming",4);
			Course course2 = new Course("software",2);
			Course course3 = new Course("database",4);
			Course course4 = new Course("C++",9);
			Course course5 = new Course("JAVA",4);
			
			Student student = new Student("100ww","Enock","wangila", new ArrayList<>(Arrays.asList(course1, course2, course3)));
			Student student1 = new Student("444TE","Caren","Mutoro", new ArrayList<>(Arrays.asList(course3, course4, course5)));
			
			list_students.add(student);
			list_students.add(student1);
			
			studentService.saveAll( list_students);			
	};
	
};
	
}
