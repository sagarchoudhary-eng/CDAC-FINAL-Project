package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkout_tbl")
public class CheckoutCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String orderId, payment_type, delivery_address;
	int userId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookId")
	Book book;
	// long ;
	int count;
	double price;
	@Column(updatable = false, insertable = false)
	String order_date;

	public CheckoutCart() {
		System.out.println("in CheckoutCart default constrctor");
	}

	public CheckoutCart(String orderId, String payment_type, String delivery_address, int userId, Book book, int count,
			double price, String order_date) {
		super();
		this.orderId = orderId;
		this.payment_type = payment_type;
		this.delivery_address = delivery_address;
		this.userId = userId;
		this.book = book;
		this.count = count;
		this.price = price;
		this.order_date = order_date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CheckoutCart [id=" + id + ", orderId=" + orderId + ", payment_type=" + payment_type
				+ ", delivery_address=" + delivery_address + ", userId=" + userId + ", book=" + book + ", count="
				+ count + ", price=" + price + ", order_date=" + order_date + "]";
	}

	
}
