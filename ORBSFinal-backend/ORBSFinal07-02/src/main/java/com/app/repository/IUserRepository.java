package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	User findByEmailIdAndPassword(String emailId, String password);

	User findByEmailId(String emailId);

}
