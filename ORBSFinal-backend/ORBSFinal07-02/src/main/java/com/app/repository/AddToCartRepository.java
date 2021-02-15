package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.AddToCart;

public interface AddToCartRepository extends JpaRepository<AddToCart, Integer> {

	@Query("Select sum(addCart.price) FROM AddToCart addCart WHERE addCart.userId=:userId")
	double getTotalAmountByUserId(@Param("userId") int userId);

	@Query("Select addCart  FROM AddToCart addCart WHERE addCart.userId=:userId")
	List<AddToCart> getCartByuserId(@Param("userId") int userId);

	@Query("Select addCart  FROM AddToCart addCart ")
	Optional<AddToCart> getCartByuserIdtest();

	@Query("Select addCart  FROM AddToCart addCart WHERE addCart.book.bookId= :bookId and addCart.userId=:userId")
	Optional<AddToCart> getCartByBookIdAndUserId(@Param("userId") int userId, @Param("bookId") int bookId);

	@Modifying
	@Transactional
	@Query("DELETE  FROM AddToCart addCart WHERE addCart.cartLineId =:cartLineId   and addCart.userId=:userId")
	void deleteCartByIdAndUserId(@Param("userId") int userId, @Param("cartLineId") int cartId);

	@Modifying
	@Transactional
	@Query("DELETE  FROM AddToCart addCart WHERE   addCart.userId=:userId")
	void deleteAllCartByUserId(@Param("userId") int userId);

	@Modifying
	@Transactional
	@Query("DELETE  FROM AddToCart addCart WHERE addCart.userId=:userId")
	void deleteAllCartUserId(@Param("userId") int userId);

	@Modifying
	@Transactional
	@Query("update AddToCart addCart set addCart.count=:count,addCart.price=:price WHERE addCart.id=:cartLineId")
	void updateCountByCartLineId(@Param("cartLineId") int cartLineId, @Param("price") double price,
			@Param("count") Integer count);

}
