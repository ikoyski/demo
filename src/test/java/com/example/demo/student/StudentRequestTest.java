package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentRequestTest {

	@Test
	@DisplayName("StudentRequestTest.studentRequestSuccess()")
	void studentSuccess() {
		// given
		final String NAME = "Peter";
		final String EMAIL = "peter@gmail.com";
		final LocalDate DOB = LocalDate.of(2000, Month.JANUARY, 5);
		StudentRequest studentREquest = new StudentRequest();

		// when
		studentREquest.setName(NAME);
		studentREquest.setEmail(EMAIL);
		studentREquest.setDob(DOB);

		// then
		Assertions.assertTrue(studentREquest.getName().equals(NAME));
		Assertions.assertTrue(studentREquest.getEmail().equals(EMAIL));
		Assertions.assertTrue(studentREquest.getDob().equals(DOB));

		Assertions.assertTrue(studentREquest.toString()
				.equals("StudentRequest [name=" + NAME + ", email=" + EMAIL + ", dob=" + DOB + "]"));
	}

}
