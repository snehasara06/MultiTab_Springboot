package com.kgisl.CourseManagement.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgisl.CourseManagement.DTO.StudentDTO;
import com.kgisl.CourseManagement.entity.Course;
import com.kgisl.CourseManagement.entity.Student;
import com.kgisl.CourseManagement.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class StudentService {
	
	@Autowired
	StudentRepository StudentRepo;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    
// GET ALL STUDENTS
	public List<Student> getAllStudents() {
		List<Student> students=StudentRepo.findAll();
		logger.info("------------------All Students-------------"+students);
		return students;
	}
	
	
	
// GET STUDENT BY ID
	public Optional<Student> getStudentById(Long id) {
	       return StudentRepo.findById(id);
    }
	 
	 
	 
// ADD STUDENT
	public ResponseEntity<String> addStudent(Student student) {
		ResponseEntity<String> response;
		try {
	      StudentRepo.save(student);
	       response =  ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Student Record Created Successfully on Database");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			response = ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Error Creating Student record");
		}
		return response;
	}
// UPDATE STUDENT
	public void updateStudentDetails(Long id, Map<String, Map<String, Object>> formdata,MultipartFile file) {
	    // Retrieve the existing student from the database
		
		System.out.println("::>>>>>>>>>>>>>>>>>"+formdata);
		
//		ObjectMapper mapper = new ObjectMapper();
//
//		Map<String, Object> map = 
//		    mapper.convertValue(formdata, new TypeReference<Map<String, Object>>() {});
//
//		StudentDTO dto = mapper.convertValue(map, StudentDTO.class);
//		
//		logger.info("DTTTTTTTTTTTTTTTTTTTTTTTTTOOOOOOOOOOO"+dto);
		
		try {
		
//			Student student = new Student();

//			List<String> allCourses = (List<String>) form2data.get("technicalSkills");
//			List<Course> courses = allCourses.stream().map(courseName -> {
//				Course course = new Course();
//				course.setName(courseName);
//				course.setStudent(student);
//				return course;
//			}).collect(Collectors.toList());

			Optional<Student> optionalStudent = StudentRepo.findById(id);

		    if (optionalStudent.isPresent()) {
		    	Map<String, Object> form1data = formdata.get("userInfo");
				Map<String, Object> form2data = formdata.get("academicInfo");
				
				
				
		        Student existingStudent = optionalStudent.get();
//				student.setFirstName((String) form1data.get("firstName"));

		        existingStudent.setStudent_id(id);
		        existingStudent.setFirstName((String) form1data.get("firstName"));
		        existingStudent.setLastName((String) form1data.get("lastName"));

		        existingStudent.setEmail((String) form1data.get("email"));
		        existingStudent.setPhone((String) form1data.get("phone"));

		        existingStudent.setAddress((String) form1data.get("address"));
		        existingStudent.setState((String) form1data.get("state"));
		        existingStudent.setPincode((String) form1data.get("pincode"));

		        existingStudent.setDob((String) form1data.get("dob"));
		        existingStudent.setGender((String) form1data.get("gender"));

		        existingStudent.setDegree((String) form2data.get("degree"));
		        existingStudent.setBoard((String) form2data.get("board"));
		        existingStudent.setSubject((String) form2data.get("subject"));
		        existingStudent.setYear((String) form2data.get("year"));
		        existingStudent.setPercentage((String) form2data.get("percentage"));
		        
		        
		        List<String> updatedCourseNames = (List<String>) form2data.get("technicalSkills");
	    		List<Course> existingCourses = existingStudent.getCourses();
 
	    		// Iterate through existing courses and remove deselected courses
	    		Iterator<Course> iterator = existingCourses.iterator();
	    		while (iterator.hasNext()) {
	    		    Course course = iterator.next();
	    		    if (!updatedCourseNames.contains(course.getName())) {
	    		        // Remove course from the list and update its relationship to null
	    		        course.setStudent(null);
	    		        iterator.remove();
	    		    }
	    		}
 
	    		// Create and add new courses
	    		for (String courseName : updatedCourseNames) {
	    		    // Check if the course already exists, if not, create a new one
	    		    boolean courseExists = existingCourses.stream().anyMatch(course -> course.getName().equals(courseName));
	    		    if (!courseExists) {
	    		        Course newCourse = new Course();
	    		        newCourse.setName(courseName);		
	    		        newCourse.setStudent(existingStudent);
	    		        existingCourses.add(newCourse);
	    		    }
	    		}
 
	    		// Update the student's courses
	    		existingStudent.setCourses(existingCourses);
		        
	    		
	    		if(file!=null) {
	    			byte[]fd=file.getBytes();
	    			existingStudent.setFileData(fd);
	    		}
//		        existingStudent.setFileData(;
//		        existingStudent.setCourses(courses); 
		        
//		        existingStudent.setFirstName(((StudentDTO) formdata).getFirstName());
//		        existingStudent.setLastName(((StudentDTO) formdata).getLastName());
//		        existingStudent.setEmail(((StudentDTO) formdata).getEmail());
//		        existingStudent.setPhone(((StudentDTO) formdata).getPhone());
//		        existingStudent.setDob(((StudentDTO) formdata).getDob());
//		        existingStudent.setGender(((StudentDTO) formdata).getGender());
//		        existingStudent.setAddress(((StudentDTO) formdata).getAddress());
////		        existingStudent.setCity(updatedStudent.getCity());
//		        existingStudent.setState(((StudentDTO) formdata).getState());
//		        existingStudent.setPincode(((StudentDTO) formdata).getPincode());
//		        existingStudent.setFileData(((StudentDTO) formdata).getFileData());
		        
		        
		        
		        
		        StudentRepo.save(existingStudent);
		    } else {
		        System.out.println("Student not found with id: " + id);
		    }		}
		catch(Exception e) {
			System.out.println(e);
		}
	    
	}
	
	
// DELETE STUDENT
	public void deleteById(Long id) {
		if (StudentRepo.existsById(id)) {
			StudentRepo.deleteById(id);
			System.out.println("Deleted successfully");
        } else {
            throw new EntityNotFoundException("Student with ID " + id + " not found");
        }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public Student getStudentByEmail(String email) {
//		return	StudentRepo.findByEmail(email);
//	}
//	  public void deleteStudentByEmail(String email) {
//	        Student student = StudentRepo.findByEmail(email);
//	       try {
//	    	   if (student != null) {
//	            StudentRepo.delete(student);
//	    	   		} 
//	       	}catch(Exception ex) {
//				System.out.println(ex.getMessage());
////	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with email: " + email);
//
//	    }
//	  }
//	  public ResponseEntity<String> updateStudent(Long id, Student updatedStudent) {
//	        Optional<Student> existingStudentOptional = StudentRepo.findById(id);
//
//	        if (existingStudentOptional.isPresent()) {
//	            Student existingStudent = existingStudentOptional.get();
//
//	            // Update properties manually
//	            existingStudent.setFirstName(updatedStudent.getFirstName());
//	            existingStudent.setLastName(updatedStudent.getLastName());
////	            existingStudent.setAddress(updatedStudent.getAddress());
//	            // Update other properties...
//
//	            StudentRepo.save(existingStudent);
////	            ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Student Record Created Successfully on Database");
//	            return ResponseEntity.status(HttpStatus.OK).body("Student Record Updated Successfully");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with id: " + id);
//	        }
//	    }
	  
	  
//	  public Student updateStudent(Long id, Student updatedStudent) {
//		    // Logic to fetch the existing student from the database
//		    Optional<Student> existingStudentOptional = StudentRepo.findById(id);
//
//		    if (existingStudentOptional.isPresent()) {
//		        Student existingStudent = existingStudentOptional.get();
//		        // Update the properties of the existing student with the new values
//		        existingStudent.setFirstName(updatedStudent.getFirstName());
//		        existingStudent.setLastName(updatedStudent.getLastName());
//		        // Update other properties as needed
//
//		        // Save the updated student to the database
//		        return StudentRepo.save(existingStudent);
//		    } else {
//		        // Handle the case where the student with the given ID is not found
//		        return null;
//		    }
//		}
//	  public Student updateStudentByEmail(String email, Student updatedStudent) {
//		    Student existingStudent = StudentRepo.findByEmail(email);
//
//		    if (existingStudent != null) {
//		        // Update the properties of the existing student with the new values
//		        existingStudent.setFirstName(updatedStudent.getFirstName());
//		        existingStudent.setLastName(updatedStudent.getLastName());
//		        // Update other properties as needed
//
//		        // Save the updated student to the database
//		        return StudentRepo.save(existingStudent);
//		    } else {
//		        // Handle the case where the student with the given email is not found
//		        return null;
//		    }
//		}

}
