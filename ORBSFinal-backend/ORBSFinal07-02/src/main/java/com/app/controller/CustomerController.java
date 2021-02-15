package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.User;
import com.app.service.IUserService;

import dto.UserDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private IUserService user_service;

	public CustomerController() {
		System.out.println("In Customer Controller...");
	}

	@GetMapping("/getAllCust")
	public ResponseEntity<?> getAllCustomers() {
		System.out.println("in getAllStudent method");

		List<UserDto> dto = new ArrayList<>();
		List<User> users = user_service.getAllUsers();
		if (users.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		for (User user : users) {
			UserDto dwnldUser = new UserDto(user.getUserId(), user.getName(), user.getEmailId(), user.getPhoneNo(),
					user.getRole());

			dto.add(dwnldUser);
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		String mesg = user_service.deleteById(id);
		return new ResponseEntity<>(mesg, HttpStatus.OK);

	}
}
