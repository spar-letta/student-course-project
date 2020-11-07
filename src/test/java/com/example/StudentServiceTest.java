package com.example;
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Course;
import com.example.model.Student;
import com.example.repository.CourseRepository;
import com.example.repository.StudentRepository;
import com.example.service.impl.StudentServiceImpl;
@RunWith(SpringRunner.class)
public class StudentServiceTest {
	@MockBean
	private CourseRepository courseRepository;
	
	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	@Test
	public void createStudentSuccessfull() {
		Student newStudent = new Student("712teb", "Palmer", "Tess");
		when(studentRepository.findByLastname("Tess")).thenReturn(newStudent);
		
		List<Course> courseToAdd = new ArrayList<>(); 
		courseToAdd.add(new Course("programming", 2));
		courseToAdd.add(new Course("software", 5));
		courseToAdd.add(new Course("database", 3));
		
		newStudent.getCourse().addAll(courseToAdd);
		
		studentServiceImpl.save(newStudent);
		

		assertEquals(3, newStudent.getCourse().size());
		verify(studentRepository, times(1)).save(newStudent);
	}

	@Test
	public void testFindStudentByLastname() {
		Student newStudent = new Student("712teb", "Palmer", "Tess");
		when(studentRepository.findByLastname("Tess")).thenReturn(newStudent);
		
		Student ss = studentServiceImpl.findByLastname("Tess");
		
		assertEquals("712teb", ss.getRegno());
		assertEquals("Palmer", ss.getFirstname());
		assertEquals("Tess", ss.getLastname());
	}
	
	@Test
	public void testFindAllStudents() {
		List<Student> listStudents = new ArrayList<>(); 
		listStudents.add(new Student("712teb", "Palmer", "Tess"));
		listStudents.add(new Student("56da", "Junior", "Wafula"));
		listStudents.add(new Student("724de", "Prince", "Evans"));		
		
		when(studentRepository.findAll()).thenReturn(listStudents);
		
		List<Student> listEmpty = studentServiceImpl.findAllStudents();
		
		assertEquals(3, listEmpty.size());
	}
	
	
}
