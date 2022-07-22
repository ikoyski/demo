package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	@DisplayName("DemoApplicationTests.contextLoads()")
	void contextLoads() {
	}

	@Test
	@DisplayName("DemoApplicationTests.mainSuccess()")
	void mainSuccess() {
		// when
		DemoApplication.main(new String[] {});
	}
}
