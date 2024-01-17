package com.kgisl.CourseManagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

@Entity
//@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
    
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;    
    
//    @Column
//    private String city;

    @Column
    private String state;

    @Column
    private String pincode;

    @Column
    private String phone;

    @Column
    private String dob;

    @Column
    private String gender;

    @Column
    private String email;

    @Column
    private String degree;

    @Column
    private String board;

    @Column
    private String year;

    @Column
    private String subject;

    @Column
    private String percentage;

    @Column(name = "file_data", columnDefinition = "BLOB", length = 10485760) 
    private byte[] fileData;

//    private byte[] fileData;
    
    @Column
    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)

    private List<Course> courses;

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Student() {
		super();
	}

	public Student(Long student_id, String firstName, String lastName, String address, String state,
			String pincode, String phone, String dob, String gender, String email, String degree, String board,
			String year, String subject, String percentage, byte[] fileData, List<Course> courses) {
		super();
		this.student_id = student_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
//		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.phone = phone;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.degree = degree;
		this.board = board;
		this.year = year;
		this.subject = subject;
		this.percentage = percentage;
		this.fileData = fileData;
		this.courses = courses;
	}

	public Student(String firstName, String lastName, String address, String state, String pincode,
			String phone, String dob, String gender, String email, String degree, String board, String year,
			String subject, String percentage, byte[] fileData, List<Course> courses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
//		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.phone = phone;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.degree = degree;
		this.board = board;
		this.year = year;
		this.subject = subject;
		this.percentage = percentage;
		this.fileData = fileData;
		this.courses = courses;
	}
    
    
}