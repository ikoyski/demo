package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {

	public final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public Student getStudent(Long studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"student with id " + studentId + " does not exists", null));
	}

	public void addNewStudent(StudentRequest studentRequest) {
		Optional<Student> studentByOptional = studentRepository.findStudentByEmail(studentRequest.getEmail());

		if (studentByOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "email already taken", null);
		}

		Student student = new Student();
		student.setName(studentRequest.getName());
		student.setEmail(studentRequest.getEmail());
		student.setDob(studentRequest.getDob());

		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student with id " + studentId + " does not exists",
					null);
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"student with id " + studentId + " does not exists", null));

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentByOptional = studentRepository.findStudentByEmail(email);
			if (studentByOptional.isPresent()) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "email already taken", null);
			}
			student.setEmail(email);
		}
	}

}
