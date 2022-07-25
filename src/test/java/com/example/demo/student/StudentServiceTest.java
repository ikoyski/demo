package com.example.demo.student;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
class StudentServiceTest {

	@Autowired
	private StudentService studentService;

	@MockBean
	private StudentRepository studentRepository;

	@Test
	@DisplayName("StudentServiceTest.getStudentsSuccess()")
	void getStudentsSuccess() {
		// given
		final Long ID = 1L;
		List<Student> giventStudents = new ArrayList<>();
		giventStudents.add(new Student(ID, "Peter", "peter@gmail.com", LocalDate.of(2000, Month.JANUARY, 5)));
		doReturn(giventStudents).when(studentRepository).findAll();

		// when
		List<Student> students = studentService.getStudents();

		// then
		Assertions.assertEquals(1, students.size());
	}

	@Test
	@DisplayName("StudentServiceTest.getStudentSuccess()")
	void getStudentSuccess() {
		// given
		final Long ID = 1L;
		Optional<Student> studentOptional = Optional
				.ofNullable(new Student(ID, "Peter", "peter@gmail.com", LocalDate.of(2000, Month.JANUARY, 5)));
		doReturn(studentOptional).when(studentRepository).findById(ID);

		// when
		Student student = studentService.getStudent(ID);

		// then
		Assertions.assertEquals(student.getId(), ID);
	}

	@Test
	@DisplayName("StudentServiceTest.getStudentNull()")
	void getStudentNull() {
		// given
		final Long ID = 1L;
		Optional<Student> studentOptional = Optional.ofNullable(null);
		doReturn(studentOptional).when(studentRepository).findById(ID);

		// when and then
		Assertions.assertThrows(ResponseStatusException.class, () -> studentService.getStudent(ID));
	}

	@Test
	@DisplayName("StudentServiceTest.addNewStudentSuccess()")
	void addNewStudentSuccess() {
		// given
		final String EMAIL = "peter@gmail.com";
		Optional<Student> studentOptional = Optional.ofNullable(null);
		doReturn(studentOptional).when(studentRepository).findStudentByEmail(EMAIL);

		// when
		studentService.addNewStudent(new StudentRequest("Peter", EMAIL, LocalDate.of(2000, Month.JANUARY, 5)));

		// then
		verify(studentRepository).findStudentByEmail(EMAIL);
	}

	@Test
	@DisplayName("StudentServiceTest.addNewStudentExists()")
	void addNewStudentExists() {
		// given
		final String EMAIL = "peter@gmail.com";
		Optional<Student> studentOptional = Optional
				.ofNullable(new Student("Peter", EMAIL, LocalDate.of(2000, Month.JANUARY, 5)));
		doReturn(studentOptional).when(studentRepository).findStudentByEmail(EMAIL);

		// when and then
		Assertions.assertThrows(ResponseStatusException.class, () -> studentService
				.addNewStudent(new StudentRequest("Peter", EMAIL, LocalDate.of(2000, Month.JANUARY, 5))));
	}

	@Test
	@DisplayName("StudentServiceTest.deleteStudentSuccess()")
	void deleteStudentSuccess() {
		// given
		final Long ID = 1L;
		doReturn(true).when(studentRepository).existsById(ID);

		// when
		studentService.deleteStudent(ID);

		// then
		verify(studentRepository).deleteById(ID);
	}

	@Test
	@DisplayName("StudentServiceTest.deleteStudentNotExists()")
	void deleteStudentNotExists() {
		// given
		final Long ID = 1L;
		doReturn(false).when(studentRepository).existsById(ID);

		// when and then
		Assertions.assertThrows(ResponseStatusException.class, () -> studentService.deleteStudent(ID));
	}

	@Test
	@DisplayName("StudentServiceTest.updateStudentSuccess()")
	void updateStudentSuccess() {
		// given
		final Long ID = 1L;
		Optional<Student> studentOptional = Optional
				.ofNullable(new Student(ID, "Peter", "peter@gmail.com", LocalDate.of(2000, Month.JANUARY, 5)));
		doReturn(studentOptional).when(studentRepository).findById(ID);

		final String EMAIL = "peter1@gmail.com";
		Optional<Student> studentOptional2 = Optional.ofNullable(null);
		doReturn(studentOptional2).when(studentRepository).findStudentByEmail(EMAIL);

		// when
		studentService.updateStudent(ID, "Peter1", "peter1@gmail.com");

		// then
		verify(studentRepository).findStudentByEmail(EMAIL);
	}

	@Test
	@DisplayName("StudentServiceTest.updateStudentNotExists()")
	void updateStudentNotExists() {
		// given
		final Long ID = 1L;
		Optional<Student> studentOptional = Optional.ofNullable(null);
		doReturn(studentOptional).when(studentRepository).findById(ID);

		// when and then
		Assertions.assertThrows(ResponseStatusException.class,
				() -> studentService.updateStudent(ID, "Peter1", "peter1@gmail.com"));
	}

	@Test
	@DisplayName("StudentServiceTest.updateStudentEmailAlreadyTaken()")
	void updateStudentEmailAlreadyTaken() {
		// given
		final Long ID = 1L;
		Optional<Student> studentOptional = Optional
				.ofNullable(new Student(ID, "Peter", "peter@gmail.com", LocalDate.of(2000, Month.JANUARY, 5)));
		doReturn(studentOptional).when(studentRepository).findById(ID);

		final String EMAIL = "peter1@gmail.com";
		Optional<Student> studentOptional2 = Optional
				.ofNullable(new Student(ID + 1, "Peter1", "peter1@gmail.com", LocalDate.of(2000, Month.JANUARY, 5)));
		doReturn(studentOptional2).when(studentRepository).findStudentByEmail(EMAIL);

		// when and then
		Assertions.assertThrows(ResponseStatusException.class,
				() -> studentService.updateStudent(ID, "Peter1", "peter1@gmail.com"));
	}

}
