package com.example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String regno;
	private String firstname;
	private String lastname;
	
	@ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "student_course",
			joinColumns = { @JoinColumn(name = "student_id",referencedColumnName = "id",
            nullable = false, updatable = false )},
			inverseJoinColumns = { @JoinColumn(name= "course_id", referencedColumnName = "id",
                nullable = false, updatable = false)
            })	
	List<Course> course = new ArrayList<>();
	
	public Student() {
		
	}

	public Student(String regno, String firstname, String lastname) {
		this.regno = regno;
		this.firstname = firstname;
		this.lastname = lastname;
		}	

	public Student(String regno, String firstname, String lastname, List<Course> course) {
		this.regno = regno;
		this.firstname = firstname;
		this.lastname = lastname;
		this.course = course;
	}

	

	public Student(Long id, String regno, String firstname, String lastname) {
		super();
		this.id = id;
		this.regno = regno;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", regno=" + regno + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", course=" + course + "]";
	}


}
