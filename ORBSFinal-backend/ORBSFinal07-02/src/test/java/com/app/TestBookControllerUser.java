package com.app;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.app.controller.BookControllerUser;
import com.app.pojos.Book;
import com.app.service.IBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = BookControllerUser.class)
@ExtendWith(SpringExtension.class)
class TestBookControllerUser {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private IBookService bookService;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private BookControllerUser bookControllerUser;

	@Test
	void smokeTest() {
		assertNotNull(bookControllerUser); // To confirm if ProductController is autowired correctly.
	}

	/*
	 * @Test public void testShowAllBooks() throws Exception { List<Book> bookList =
	 * new ArrayList<>(); when(bookService.getAllBooks()).thenReturn(bookList);
	 * MvcResult result =
	 * mvc.perform(get("/user/books")).andDo(print()).andExpect(status().isOk()).
	 * andReturn(); }
	 */

//	@Test
//	public void testShowBookByCategory() throws Exception {
//		Book book = new Book("JAVA-8546-19-3", "Dreamtech Publisher", Date.valueOf("2019-10-16"), 3, 526.50,
//				"Core & Adv Java", "engineering", 5);
//		// List<Book> book = new ArrayList<Book>();
//		when(bookService.findBookByBookId(1)).thenReturn(book);
//		MvcResult result = mvc.perform(get("/user/category/engineering")).andDo(print()).andExpect(status().isOk())
//				.andReturn();
//	}

}
