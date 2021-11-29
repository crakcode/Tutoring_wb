package com.example.demo;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

@SpringBootTest(classes=SpringSecurityJwtApplicationTests.class)
class SpringSecurityJwtApplicationTests {
	
	@Test
	void contextLoads() throws IOException {
		
		String path = new ClassPathResource("/static/images").getFile().getAbsolutePath();
		System.out.println(path);
	}

}
