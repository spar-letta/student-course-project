package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Course;
import com.example.model.Student;
import com.example.service.StudentService;

import jdk.net.SocketFlow.Status;

@RestController
public class StudentController {

	private StudentService studentService;
	
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	// find all students
	@GetMapping(value = "/students")
	public ResponseEntity<List<Student>> findAllStudents(){
		try {
			List<Student> emptyList = new ArrayList<>();
			studentService.findAllStudents().forEach(emptyList :: add);
			if(emptyList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}			
			return new ResponseEntity<List<Student>>(emptyList, HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//find student by lastname
	@GetMapping(value = "/students/{lastname}")
	public ResponseEntity<Student> findByLastName(@PathVariable(name = "lastname") String lastname){
		try {
			Student student = studentService.findByLastname(lastname);
			if(student == null) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(student, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//create student
	@PostMapping(value ="/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		try {
			Student newStudent = studentService.save(student);
			return new ResponseEntity<Student>(newStudent, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//only students taking questioned course
	@GetMapping(value = "/students/course/{name}")
	public ResponseEntity<List<Student>> find_students_doing_course(@PathVariable("name") String name){
		try {
		List<Student> list_of_students = studentService.findStudentsByCourse(name);	
		if(list_of_students.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list_of_students, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	//update student record
	@PutMapping("/students/update/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student ){
		
			Optional<Student> student_found = studentService.findById(id);
			if(student_found.isPresent()) {
				Student updated_student = student_found.get();
				updated_student.setRegno(student.getRegno());
				updated_student.setFirstname(student.getFirstname());
				updated_student.setLastname(student.getLastname());
				
				return new ResponseEntity<>(studentService.save(updated_student), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
	}
	//crteate course
	@PostMapping(value = "/students/{id}/course")
	public ResponseEntity<Course> createCourse(@PathVariable("id") Long id, @RequestBody Course course){
		Optional<Student> student = studentService.findById(id);
		if(student.isPresent()) {
			Course newCourse = studentService.addCourse(id, course);
			return new ResponseEntity<Course>(newCourse, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}
	
	
}
