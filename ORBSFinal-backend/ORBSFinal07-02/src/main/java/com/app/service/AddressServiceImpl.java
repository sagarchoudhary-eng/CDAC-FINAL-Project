package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.User;
import com.app.repository.IAddressRepository;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private IAddressRepository addRepo;

	@Override
	public Address addAddress(int userId, Address add) {
		List<Address> addresses = new ArrayList<>();
		User user1 = new User();
		Optional<User> user = userRepo.findById(userId);

		User oldUser = user.get();
		add.setUser(oldUser);

		Address user2 = addRepo.save(add);

		return user2;

	}

}
