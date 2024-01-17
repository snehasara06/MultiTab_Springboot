package com.kgisl.CourseManagement.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgisl.CourseManagement.DTO.StudentDTO;
import com.kgisl.CourseManagement.entity.Course;
import com.kgisl.CourseManagement.entity.Student;
import com.kgisl.CourseManagement.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:4200/*")
//@CrossOrigin(origins="http://localhost:56116/*")
public class StudentController {

	@Autowired
	StudentService studentService; // Dependency injection

	private final Logger logger = LoggerFactory.getLogger(StudentService.class);

// 	GET ALL STUDENTS	
	@GetMapping("/")
	public ResponseEntity<List<StudentDTO>> getAll() {
		List<Student> allStudents = studentService.getAllStudents();

		List<StudentDTO> studentDTOs = allStudents.stream().map(student -> {

			StudentDTO sdto = new StudentDTO();
			sdto.setStudent_id(student.getStudent_id());
			sdto.setFirstName(student.getFirstName());
			sdto.setLastName(student.getLastName());

			sdto.setEmail(student.getEmail());
			sdto.setPhone(student.getPhone());

			sdto.setDob(student.getDob());
			sdto.setGender(student.getGender());

			sdto.setAddress(student.getAddress());
//			sdto.setCity(student.getCity());
			sdto.setState(student.getState());
			sdto.setPincode(student.getPincode());

			sdto.setDegree(student.getDegree());
			sdto.setSubject(student.getSubject());
			sdto.setPercentage(student.getPercentage());
			sdto.setBoard(student.getBoard());
			sdto.setYear(student.getYear());

			sdto.setCourses(student.getCourses());
//			sdto.setFileData(student.getFileData());
			sdto.setFileDataUrl("/download/"+student.getStudent_id());

			logger.info("DTO: ", sdto);
			return sdto;

		}).collect(Collectors.toList());

		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}

// ADD STUDENT
	@PostMapping("/")
	public ResponseEntity<String> addNewStudent(@RequestPart("dataToSend") Map<String, Map<String, Object>> formdata,
			@RequestParam("file") MultipartFile file) {
		try {
			Map<String, Object> form1data = formdata.get("userInfo");
			Map<String, Object> form2data = formdata.get("academicInfo");
			Student student = new Student();

			List<String> allCourses = (List<String>) form2data.get("technicalSkills");
			List<Course> courses = allCourses.stream().map(courseName -> {
				Course course = new Course();
				course.setName(courseName);
				course.setStudent(student);
				return course;
			}).collect(Collectors.toList());

			student.setFirstName((String) form1data.get("firstName"));
			student.setLastName((String) form1data.get("lastName"));

			student.setEmail((String) form1data.get("email"));
			student.setPhone((String) form1data.get("phone"));

			student.setAddress((String) form1data.get("address"));
//			student.setCity((String)form1data.get("city"));
			student.setState((String) form1data.get("state"));
			student.setPincode((String) form1data.get("pincode"));

			student.setDob((String) form1data.get("dob"));
			student.setGender((String) form1data.get("gender"));

			student.setDegree((String) form2data.get("degree"));
			student.setBoard((String) form2data.get("board"));
			student.setSubject((String) form2data.get("subject"));
			student.setYear((String) form2data.get("year"));
			student.setPercentage((String) form2data.get("percentage"));
			student.setCourses(courses);

			System.out.println("Student request:" + student);
			if (file != null) {
				byte[] fileData = file.getBytes();
				student.setFileData(fileData);
			}
			return studentService.addStudent(student);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

// GET STUDENT BY ID
	@GetMapping("getById/{id}")
	public ResponseEntity <Optional<Student>> getById(@PathVariable Long id) {
		Optional<Student> student = studentService.getStudentById(id);

//		if (student != null) {
//			StudentDTO studentDTO = new StudentDTO();
//			studentDTO.setFirstName(student.getFirstName());
//			studentDTO.setLastName(student.getLastName());
//			studentDTO.setEmail(student.getEmail());
//			studentDTO.setPhone(student.getPhone());
//			studentDTO.setDob(student.getDob());
//			studentDTO.setGender(student.getGender());
//			studentDTO.setAddress(student.getAddress());
////	        studentDTO.setCity(student.getCity());
//			studentDTO.setState(student.getState());
//			studentDTO.setPincode(student.getPincode());
//			studentDTO.setDegree(student.getDegree());
//			studentDTO.setSubject(student.getSubject());
//			studentDTO.setPercentage(student.getPercentage());
//			studentDTO.setBoard(student.getBoard());
//			studentDTO.setYear(student.getYear());
//			studentDTO.setCourses(student.getCourses());
//			studentDTO.setFileData(student.getFileData());

//			return new ResponseEntity<>(studentDTO, HttpStatus.OK);
		return student.map(stu->ResponseEntity.ok(student))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
	}

//UPDATE STUDENT
//@PutMapping(value = "/update/{id}")
	@PutMapping("/update/{id}")

	public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestParam String dataToSend,
			@RequestParam("file") MultipartFile file) {
		try {
			ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper from Jackson library
			Map<String, Map<String, Object>> formDataMap = objectMapper.readValue(dataToSend,
					new TypeReference<Map<String, Map<String, Object>>>() {
					});
			System.out.println(":::>>>>>"+formDataMap);
			studentService.updateStudentDetails(id, formDataMap, file);
			return ResponseEntity.status(HttpStatus.OK).body("Student Record Updated Successfully");
//	
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Student Record");
		}
//	return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Student Record Updated Successfully");
	}

// DELETE STUDENT
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		try {
			studentService.deleteById(id);
			return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Student Record Deleted Successfully");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Deleting Student Record");
		}
	}
	
//	DOWNLOAD FILE
	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable Long id){
		Optional<Student> student=studentService.getStudentById(id);
		if(student.isPresent()) {
			byte[] fileData=student.get().getFileData();
			ByteArrayResource resource = new ByteArrayResource(fileData);
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=file.pdf")
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.contentLength(fileData.length)
					.body(resource);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
