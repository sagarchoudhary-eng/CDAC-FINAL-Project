package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "cart_tbl")
@JsonPropertyOrder(alphabetic = true)
public class AddToCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartLineId;
	@OneToOne
	@JoinColumn(name = "bookId")
	private Book book;

	private int userId;
	private int count;
	private double price;
	@Transient
	String bookName;

	public AddToCart() {
		System.out.println("in AddTooCart default constrctor");
	}

	public AddToCart(Book book, int count, double price, String bookName) {
		super();
		this.book = book;
		this.count = count;
		this.price = price;
		this.bookName = bookName;
	}

	public Integer getCartLineId() {
		return cartLineId;
	}

	public void setCartLineId(Integer cartLineId) {
		this.cartLineId = cartLineId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddToCart [cartLineId=" + cartLineId + ", book=" + book + ", userId=" + userId + ", count=" + count
				+ ", price=" + price + ", bookName=" + bookName + "]";
	}

}
