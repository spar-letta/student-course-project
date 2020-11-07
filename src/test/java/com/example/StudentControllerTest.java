package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import static org.mockito.Matchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.controller.StudentController;
import com.example.model.Course;
import com.example.model.Student;
import com.example.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	
	@Test
	public void test_FindAllStudents() throws Exception{
		List<Student> empStd = buildStudent();
		studentService.saveAll(empStd);
		
		when(studentService.findAllStudents()).thenReturn(empStd);
		
		  mockMvc.perform(MockMvcRequestBuilders.get("/students").accept(MediaType.APPLICATION_JSON))
				 .andExpect(status().isOk())
				 .andExpect(jsonPath("$.size()", is(empStd.size())))
				 .andExpect(jsonPath("[0].regno", is("2000TR")))
				 .andExpect(jsonPath("[0].firstname", is("Peter")))
				 .andExpect(jsonPath("[0].lastname", is("Loyna")))
				 .andExpect(jsonPath("[1].regno", is("3000TR")))
				 .andExpect(jsonPath("[1].firstname", is("Fidel")))
				 .andExpect(jsonPath("[1].lastname", is("Jayna")))
				 .andExpect(jsonPath("[2].regno", is("4000TR")))
				 .andExpect(jsonPath("[2].firstname", is("Junior")))
				 .andExpect(jsonPath("[2].lastname", is("Wafula")))
				 ;
	     
		        verify(studentService).findAllStudents();
					
				assertEquals(3,empStd.size());
										
	}
	
	@Test
	public void test_FindStudentByLastName() throws Exception{
		final String last_name = "wekalayo";
		Student test_student = new Student("2000TR", "Peter", "wekalayo");
		when(studentService.findByLastname(test_student.getLastname())).thenReturn(test_student);
		
		this.mockMvc.perform(get("/students/{lastname}",last_name ))
		
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.regno", is(test_student.getRegno())))
		.andExpect(jsonPath("$.firstname", is(test_student.getFirstname())))
		.andExpect(jsonPath("$.lastname", is(test_student.getLastname())))
		;
					
	}
	//test create student
	@Test
	public void test_createStudent() throws Exception {
		Student newStudent = new Student("49000TR", "Junior", "Wafula");
		when(studentService.save(any())).thenReturn(newStudent);				
		 mockMvc.perform(
				post("/students")
				.content(asJsonString(newStudent))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(newStudent.getId())))
				.andExpect(jsonPath("$.lastname", is("Wafula")))
				;		
	}
	
	public List<Student> buildStudent(){
		Student s1 = new Student(1L,"2000TR", "Peter", "Loyna");
		Student s2 = new Student(2L, "3000TR", "Fidel", "Jayna");
		Student s3 = new Student(3L, "4000TR", "Junior", "Wafula");
		
		List<Student> all_students = Arrays.asList(s1, s2, s3);
		
		return all_students;
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
