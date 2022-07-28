package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping(path = "api/v1/students")
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@GetMapping(path = "api/v1/students/{studentId}")
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public Student getStudent(@PathVariable("studentId") Long studentId) {
		return studentService.getStudent(studentId);
	}

	@PostMapping(path = "api/v1/students")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Created")
	public void registerNewStudent(@RequestBody StudentRequest studentRequest) {
		studentService.addNewStudent(studentRequest);
	}

	@DeleteMapping(path = "api/v1/students/{studentId}")
	@ResponseStatus(code = HttpStatus.OK, reason = "Deleted")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}

	@PutMapping(path = "api/v1/students/{studentId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Updated")
	public void updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		studentService.updateStudent(studentId, name, email);
	}
}
