package com.app;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.app.controller.UserController;
import com.app.pojos.User;
import com.app.service.IAddressService;
import com.app.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(SpringExtension.class)
public class TestUserController {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private IUserService userService;
	@MockBean
	private IAddressService addrService;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private UserController userController;

	@Test
	void smokeTest() {
		assertNotNull(userController); // To confirm if ProductController is autowired correctly.
	}

	@Test
	void test() throws Exception {
		MvcResult result = mvc.perform(get("/user")).andDo(print()).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void testLoginUser() throws Exception {
		User u = new User();
		u.setEmailId("sanjeev@gmail.com");
		u.setPassword("12345");
		
		when(userService.findByEmailIdAndPassword(u.getEmailId(), u.getPassword())).thenReturn(u); 
		mvc.perform(post("/user/login"). //performs a post request
				content(jsonString(u)). //setting request body as p
				contentType(MediaType.APPLICATION_JSON)) //setting content type header
				.andExpect(status().isOk());//chks if HttpStatus is OK
	}

	private String jsonString(Object u) throws Exception {
		
		return mapper.writeValueAsString(u);
	}
	
	@Test
	void testShowUsers() throws Exception {
		MvcResult result = mvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk()).andReturn();
	}
	

}
