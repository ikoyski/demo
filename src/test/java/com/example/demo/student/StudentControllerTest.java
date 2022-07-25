package com.example.demo.student;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class StudentControllerTest {

	@Autowired
	private StudentController studentController;

	@MockBean
	private StudentService studentService;

	@Test
	@DisplayName("StudentControllerTest.getStudentsSuccess()")
	void getStudentsSuccess() {
		// when
		studentController.getStudents();

		// then
		verify(studentService).getStudents();
	}

	@Test
	@DisplayName("StudentControllerTest.getStudentSuccess()")
	void getStudentSuccess() {
		// given
		final Long ID = 1L;
		Student student = new Student(ID, "Peter", "peter@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
		doReturn(student).when(studentService).getStudent(ID);

		// when
		studentController.getStudent(ID);

		// then
		verify(studentService).getStudent(ID);
	}

	@Test
	@DisplayName("StudentControllerTest.requsterNewStudentSuccess()")
	void requsterNewStudentSuccess() {
		// given
		StudentRequest studentRequest = new StudentRequest("Peter", "peter@gmail.com",
				LocalDate.of(2000, Month.JANUARY, 5));

		// when
		studentController.registerNewStudent(studentRequest);

		// then
		verify(studentService).addNewStudent(studentRequest);
	}

	@Test
	@DisplayName("StudentControllerTest.deleteStudentSuccess()")
	void deleteStudentSuccess() {
		// given
		final Long ID = 1L;

		// when
		studentController.deleteStudent(ID);

		// then
		verify(studentService).deleteStudent(ID);
	}

	@Test
	@DisplayName("StudentControllerTest.updateStudentSuccess()")
	void updateStudentSuccess() {
		// given
		final Long ID = 1L;
		final String NAME = "Peter";
		final String EMAIL = "peter@gmail.com";

		// when
		studentController.updateStudent(ID, NAME, EMAIL);

		// then
		verify(studentService).updateStudent(ID, NAME, EMAIL);
	}

}
