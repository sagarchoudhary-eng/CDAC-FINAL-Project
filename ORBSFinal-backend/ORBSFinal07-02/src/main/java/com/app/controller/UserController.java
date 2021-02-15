package com.app.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.Address;
import com.app.pojos.User;
import com.app.service.IAddressService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IAddressService addService;

	public UserController() {
		System.out.println("In User Controller...");
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> showUsers(@PathVariable Integer id) {
		Optional<User> users = userService.findById(id);

		if (users.isPresent()) {
			User u = users.get();
			System.out.println(u);
			return new ResponseEntity<>(u, HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/login") // api: http://localhost:8080/user/login
	public ResponseEntity<?> loginUser(@RequestBody User user, Model modelMap, HttpSession hs,
			RedirectAttributes flashMap) {
		System.out.println(user.getEmailId() + " " + user.getPassword());
		try {
			User authenticateUsers = userService.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
			System.out.println(authenticateUsers);
			hs.setAttribute("valid user", authenticateUsers);
			if (authenticateUsers.getRole().equals(user.getRole().CUSTOMER)) {
				flashMap.addFlashAttribute("status", "Customer login successful..!!");
				System.out.println("address details" + authenticateUsers.getAddress());
				return ResponseEntity.ok(authenticateUsers);
			}
			flashMap.addFlashAttribute("status", "Admin login successful..!!");
			return ResponseEntity.ok(authenticateUsers);
		} catch (RuntimeException e) {
			System.out.println("Error in user controller " + e);
			modelMap.addAttribute("mesg", "Invalid Login , PlEASE RETRY!!!!!!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUserDetails(@RequestBody User u) {
		try {
			User details = userService.saveUserDetails(u);
			return new ResponseEntity<>(details, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/address/{user_id}")
	public ResponseEntity<?> saveAuthorDetails(@PathVariable int user_id, @RequestBody Address add) {
		System.out.println("in save address details" + add);
		System.out.println("user id" + user_id);
		try {
			Address address = addService.addAddress(user_id, add);
			return new ResponseEntity<>(address, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in save" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/logout")
	public String logoutPage(HttpSession session, Model modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("In logout");
		// modelMap.addAttribute("user_dtls", session.getAttribute("valid_user"));
		// session.invalidate();
		// response.setHeader("refresh", "5;url='" + request.getContextPath() + "/'");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
}
