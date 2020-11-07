package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Course;
import com.example.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> findAll(){
		try {
			List<Course> all_Courses = new ArrayList<>();
			courseService.findAll().forEach(all_Courses :: add);
			if(all_Courses.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}			
			return new ResponseEntity<>(all_Courses, HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
