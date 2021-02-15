package com.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.controller.HomeController;

@SpringBootTest
public class TestHomeController {
	
	@Autowired
	private HomeController homeController;
	
	@Test
	void contextLoads() {
		System.out.println("in Test of Home Controller ...");
		assertNotNull(homeController);
	}
}
