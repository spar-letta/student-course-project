package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.model.Course;
import com.example.model.Student;


public interface StudentService {
	
	public List<Student> findAllStudents();
	
	public Optional<Student> findById(Long id);
	
	public Student findByLastname(String lastname);
	
	public Student save(Student student);

	public List<Student> findStudentsByCourse(String name);
	
	public Student updateStudent(Student student, Long id);

	public List<Course> findAll();
	
	public Course addCourse(Long id, Course course);

	public void saveAll(List<Student> list_students);
}
