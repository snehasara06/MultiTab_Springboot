package com.kgisl.CourseManagement.DTO;

import java.util.List;

import com.kgisl.CourseManagement.entity.Course;



public class StudentDTO {
	  
	    private Long student_id;
	 
	    private String firstName;

	    private String lastName;

//	    @Column
	    private String address;    
	    
//	    @Column
//	    private String city;

//	    @Column
	    private String state;

//	    @Column
	    private String pincode;

//	    @Column
	    private String phone;

//	    @Column
	    private String dob;

//	    @Column
	    private String gender;

//	    @Column
	    private String email;

//	    @Column
	    private String degree;

//	    @Column
	    private String board;

//	    @Column
	    private String year;

//	    @Column
	    private String subject;

//	    @Column
	    private String percentage;

//	    @Column
	    private String fileDataUrl;
	    
//	    @Column
//	    @OneToMany(mappedBy = "student")

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

//		public String getCity() {
//			return city;
//		}
//
//		public void setCity(String city) {
//			this.city = city;
//		}

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

		public List<Course> getCourses() {
			return courses;
		}

		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}

		@Override
		public String toString() {
			return "StudentDTO [student_id=" + student_id + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", address=" + address + ", city="  + ", state=" + state + ", pincode=" + pincode
					+ ", phone=" + phone + ", dob=" + dob + ", gender=" + gender + ", email=" + email + ", degree="
					+ degree + ", board=" + board + ", year=" + year + ", subject=" + subject + ", percentage="
					+ percentage + ", courses=" + courses + "]";
		}

		public String getFileDataUrl() {
			return fileDataUrl;
		}

		public void setFileDataUrl(String fileDataUrl) {
			this.fileDataUrl = fileDataUrl;
		}
	    
	    
	    
}