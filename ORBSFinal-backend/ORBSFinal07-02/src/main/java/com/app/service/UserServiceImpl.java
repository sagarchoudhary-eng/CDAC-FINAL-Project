package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.User;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepos;

	@Override
	public List<User> getAllUsers() {

		return userRepos.findAll();
	}

	@Override
	public String deleteById(int id) {

		String mesg = "Student record deleted successfully";
		userRepos.deleteById(id);
		return mesg;
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepos.findById(id);
	}

	@Override
	public User findByEmailIdAndPassword(String emailId, String password) {
		return userRepos.findByEmailIdAndPassword(emailId, password);
	}

	@Override
	public User saveUserDetails(User transientUser) {
		return userRepos.save(transientUser);
	}

	@Override
	public User findByEmailId(String emailId) {
		return userRepos.findByEmailId(emailId);
	}

}
