package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student peter = new Student("Peter", "peter@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
			Student james = new Student("James", "james@gmail.com", LocalDate.of(2004, Month.JANUARY, 5));

			studentRepository.save(peter);
			studentRepository.save(james);
		};
	}

}
