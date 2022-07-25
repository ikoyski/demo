package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentTest {

	@Test
	@DisplayName("StudentTest.studentSuccess()")
	void studentSuccess() {
		// given
		final Long ID = 1L;
		final String NAME = "Peter";
		final String EMAIL = "peter@gmail.com";
		final LocalDate DOB = LocalDate.of(2000, Month.JANUARY, 5);
		final Integer AGE = Period.between(DOB, LocalDate.now()).getYears();
		Student student = new Student();

		// when
		student.setId(ID);
		student.setName(NAME);
		student.setEmail(EMAIL);
		student.setDob(DOB);
		student.setAge(AGE);

		// then
		Assertions.assertEquals(student.getId(), ID);
		Assertions.assertEquals(student.getName(), NAME);
		Assertions.assertEquals(student.getEmail(), EMAIL);
		Assertions.assertEquals(student.getDob(), DOB);
		Assertions.assertEquals(student.getAge(), AGE);

		Assertions.assertTrue(student.toString().equals(
				"Student [id=" + ID + ", name=" + NAME + ", email=" + EMAIL + ", dob=" + DOB + ", age=" + AGE + "]"));
	}

}
