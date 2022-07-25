package com.example.demo.student;

import java.time.LocalDate;

public class StudentRequest {

	private String name;
	private String email;
	private LocalDate dob;

	public StudentRequest() {
		super();
	}

	public StudentRequest(String name, String email, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "StudentRequest [name=" + name + ", email=" + email + ", dob=" + dob + "]";
	}
}
