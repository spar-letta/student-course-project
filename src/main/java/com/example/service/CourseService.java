package com.example.service;

import java.util.List;

import com.example.model.Course;

public interface CourseService {

	Course findByName(String name);
	
	List<Course> findAll();
	
	Course save(Course course);
}
