package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.app.pojos.Book;

//Integration Test : complete end to end testing
//creates a web app context (SC) using any available random free port.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestControllerTestWithServer {
	//randomly available free port is injected in local server port
	@LocalServerPort
	private int serverPort;
	// Abstraction of REST client to be used in test scenario
	@Autowired
	private TestRestTemplate template;

	@Test
	public void testTestConroller() throws Exception {

		String response = template.getForObject
				("http://localhost:" + serverPort + "/test", String.class);
		assertEquals(response, "Hello, REST !!!!");
	}
//	@Test
//	public void testProductConrollerGetProductByIDPathVar()  {
//
//		Book book = template.getForObject
//				("http://localhost:" + serverPort + "/book/2", Book.class);
//		assertEquals("mango", book.getTitle());
//	}
	
	//for testing named query 
//	@Test
//	public void testProductConrollerGetProductByName()
//	{
//		Book book = template.getForObject
//				("http://localhost:" + serverPort + "/category/commerce", Book.class);
//		assertEquals(120,book.getPrice());
//	}

}
