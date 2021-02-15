package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojos.User;

public interface IUserService {
	List<User> getAllUsers();

	String deleteById(int id);

	Optional<User> findById(Integer id);

	User findByEmailIdAndPassword(String emailId, String password);

	User saveUserDetails(User transientUser);

	// void save(User user);

	User findByEmailId(String emailId);
}
