package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.CheckoutCart;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutCart, Integer> {
	@Query("Select checkCart  FROM CheckoutCart checkCart WHERE checkCart.userId=:userId")
	List<CheckoutCart> getByuserId(@Param("userId") int userId);
}
