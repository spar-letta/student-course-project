package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByLastname(String lastname);
	
		
	@Query(value = "SELECT s.* FROM student s JOIN student_course sc ON s.id = sc.student_id JOIN course c ON sc.course_id = c.id WHERE c.name = :name", nativeQuery = true)
	List<Student> findByCourse(@Param("name") String name);

}
