package com.example.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Course;
import com.example.model.Student;
import com.example.repository.CourseRepository;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Student findByLastname(String lastname) {
		return studentRepository.findByLastname(lastname);
	}

	@Override
	public Student save(Student student) {	
		return studentRepository.save(student);
	}

	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> findStudentsByCourse(String name) {
		
		return studentRepository.findByCourse(name);
	}

	@Override
	public Student updateStudent(Student student, Long id) {
		return studentRepository.save(student);
	}

	@Override
	public Optional<Student> findById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public Course addCourse(Long id, Course course) {
		Optional<Student> student = findById(id);
		if(student.isPresent()) {		
			
			student.get().getCourse().add(course);
			
			courseRepository.save(course);
			return course;
		}
		return null;
	}

	@Override
	public void saveAll(List<Student> list_students) {
		
		studentRepository.saveAll(list_students);
	}
	
	

}
