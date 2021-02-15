package com.app.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.jwtConfiguration.ShoppingConfiguration;
import com.app.pojos.AddToCart;
import com.app.service.ICartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

	@Autowired
	ICartService cartService;

	public CartController() {
		System.out.println("In Cart Controller...");
	}

	@RequestMapping("/addBook")
	public ResponseEntity<?> addCartwithBook(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = { "bookId", "userId", "count", "price" };
			if (ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

			}
			int bookId = Integer.parseInt(addCartRequest.get("bookId"));
			int userId = Integer.parseInt(addCartRequest.get("userId"));
			int count = Integer.parseInt(addCartRequest.get("count"));
			double price = Double.parseDouble(addCartRequest.get("price"));
			List<AddToCart> obj = cartService.addCartByBookIdAndUserId(bookId, userId, count, price);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(addCartRequest, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping("/updateCountForCart")
	public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = { "cartId", "userId", "qty", "price" };
			if (ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

			}
			int cartId = Integer.parseInt(addCartRequest.get("cartId"));
			int userId = Integer.parseInt(addCartRequest.get("userId"));
			int count = Integer.parseInt(addCartRequest.get("count"));
			double price = Double.parseDouble(addCartRequest.get("price"));
			cartService.updateCountByCartLineId(cartId, price, count);
			List<AddToCart> obj = cartService.getCartByUserId(userId);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(addCartRequest, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping("removeProductFromCart")
	public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String, String> removeCartRequest) {
		try {
			String keys[] = { "userId", "cartId" };
			if (ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

			}
			List<AddToCart> obj = cartService.removeCartByUserId(Integer.parseInt(removeCartRequest.get("cartId")),
					Integer.parseInt(removeCartRequest.get("userId")));
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			return new ResponseEntity<>(removeCartRequest, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/getCartsByUserId")
	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String, String> getCartRequest) {
		try {
			String keys[] = { "userId" };
			if (ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
			}
			List<AddToCart> obj = cartService.getCartByUserId(Integer.parseInt(getCartRequest.get("userId")));
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			return new ResponseEntity<>(getCartRequest, HttpStatus.BAD_REQUEST);
		}
	}
}
