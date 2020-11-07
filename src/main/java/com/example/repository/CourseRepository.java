package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Course;


public interface CourseRepository extends JpaRepository<Course, Long>{

	Course findByName(String name);
	
	//List<Course> findByTitleContaining(String title);
}
