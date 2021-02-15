package com.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Book;
import com.app.pojos.CheckoutCart;
import com.app.repository.AddToCartRepository;
import com.app.repository.CheckoutRepository;
import com.app.pojos.AddToCart;

@Service
@Transactional
public class CartServiceImpl implements ICartService {

	@Autowired
	AddToCartRepository addCartRepo;
	@Autowired
	CheckoutRepository checkOutRepo;
	@Autowired
	IBookService bookService;

	// private static final Logger logger =
	// LoggerFactory.getLogger(CartSerivceImpl.class);
	@Override
	public List<AddToCart> addCartByBookIdAndUserId(int bookId, int userId, int count, double price) throws Exception {
		try {
			if (addCartRepo.getCartByBookIdAndUserId(userId, bookId).isPresent()) {
				throw new Exception("Product is already exist.");
			}
			AddToCart obj = new AddToCart();
			obj.setCount(count);
			obj.setUserId(userId);
			Book book = bookService.findBookByBookId(bookId);
			obj.setBook(book);
			// TODO price has to check with qty
			obj.setPrice(price);
			addCartRepo.save(obj);
			return this.getCartByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error(""+e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void updateCountByCartLineId(int cartLineId, double price, int count) throws Exception {
		addCartRepo.updateCountByCartLineId(cartLineId, price, count);

	}

	@Override
	public List<AddToCart> getCartByUserId(int userId) {
		return addCartRepo.getCartByuserId(userId);
	}

	@Override
	public List<AddToCart> removeCartByUserId(int cartLineId, int userId) {
		addCartRepo.deleteCartByIdAndUserId(userId, cartLineId);
		return this.getCartByUserId(userId);
	}

	@Override
	public List<AddToCart> removeAllCartByUserId(int userId) {
		addCartRepo.deleteAllCartByUserId(userId);
		return null;
	}

	@Override
	public Boolean checkTotalAmountAgainstCart(double totalAmount, int userId) {
		double total_amount = addCartRepo.getTotalAmountByUserId(userId);
		if (total_amount == totalAmount) {
			return true;
		}
		System.out.print("Error from request " + total_amount + " --db-- " + totalAmount);
		return false;
	}

	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(int userId) {
		return checkOutRepo.getByuserId(userId);
	}

	@Override
	public List<CheckoutCart> saveBookForCheckout(List<CheckoutCart> tmp) throws Exception {
		try {
			int userId = tmp.get(0).getUserId();
			if (tmp.size() > 0) {
				checkOutRepo.saveAll(tmp);
				this.removeAllCartByUserId(userId);
				return this.getAllCheckoutByUserId(userId);
			} else {
				throw new Exception("Should not be empty");
			}
		} catch (Exception e) {
			throw new Exception("Error while checkout " + e.getMessage());
		}

	}

}
