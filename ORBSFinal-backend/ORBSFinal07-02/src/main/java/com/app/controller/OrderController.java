package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.jwtConfiguration.ShoppingConfiguration;
import com.app.pojos.AddToCart;
import com.app.pojos.CheckoutCart;
import com.app.service.IBookService;
import com.app.service.ICartService;

@RestController
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	ICartService cartService;
	IBookService proService;

	public OrderController() {
		System.out.println("in Order Controller...");
	}

	@RequestMapping("checkout_order")
	public ResponseEntity<?> checkout_order(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = { "userId", "total_price", "pay_type", "deliveryAddress" };
			if (ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

			}
			int userId = Integer.parseInt(addCartRequest.get("userId"));
			double total_amt = Double.parseDouble(addCartRequest.get("total_price"));
			if (cartService.checkTotalAmountAgainstCart(total_amt, userId)) {
				List<AddToCart> cartItems = cartService.getCartByUserId(userId);
				List<CheckoutCart> tmp = new ArrayList<CheckoutCart>();
				for (AddToCart addCart : cartItems) {
					String orderId = "" + getOrderId();
					CheckoutCart cart = new CheckoutCart();
					cart.setPayment_type(addCartRequest.get("pay_type"));
					cart.setPrice(total_amt);
					cart.setUserId(userId);
					cart.setOrderId(orderId);
					cart.setBook(addCart.getBook());
					cart.setCount(addCart.getCount());
					cart.setDelivery_address(addCartRequest.get("deliveryAddress"));
					tmp.add(cart);
				}
				cartService.saveBookForCheckout(tmp);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				throw new Exception("Total amount is mismatch");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(addCartRequest, HttpStatus.BAD_REQUEST);
		}
	}

	public int getOrderId() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}

	@RequestMapping("getOrdersByUserId")
	public ResponseEntity<?> getOrdersByUserId(@RequestBody HashMap<String, String> ordersRequest) {
		try {
			String keys[] = { "userId" };
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
