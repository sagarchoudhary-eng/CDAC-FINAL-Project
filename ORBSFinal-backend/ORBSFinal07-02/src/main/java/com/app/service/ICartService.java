package com.app.service;

import java.util.List;
import com.app.pojos.AddToCart;
import com.app.pojos.CheckoutCart;

public interface ICartService {
	List<AddToCart> addCartByBookIdAndUserId(int bookId, int userId, int count, double price) throws Exception;

	void updateCountByCartLineId(int cartLineId, double price, int count) throws Exception;

	List<AddToCart> getCartByUserId(int userId);

	List<AddToCart> removeCartByUserId(int cartLineId, int userId);

	List<AddToCart> removeAllCartByUserId(int userId);

	Boolean checkTotalAmountAgainstCart(double totalAmount, int userId);

	List<CheckoutCart> getAllCheckoutByUserId(int userId);

	List<CheckoutCart> saveBookForCheckout(List<CheckoutCart> tmp) throws Exception;

}
