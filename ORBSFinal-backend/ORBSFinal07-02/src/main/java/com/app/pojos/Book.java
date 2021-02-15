package com.app.pojos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "book_tbl")
@JsonPropertyOrder(alphabetic = true)
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer bookId;
	@Column(length = 20, nullable = false)
	private String isbn;
	@Column(length = 50, nullable = false)
	private String publisher;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date year;
	private int edition;
	@Column(columnDefinition = "double(10,2)")
	private double price;
	@Column(length = 50, nullable = false, unique = true)
	private String title;
	@Column(length = 50, nullable = false)
	private String category;
	@Column(nullable = false)
	private int bookInStock;
	@Lob
	@Column(name = "book_image")
	private byte[] image;
	@Column(name = "image_type", length = 30)
	private String type;

	@JsonManagedReference
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("book")
	private List<Author> authors = new ArrayList<>();

	public Book() {
		System.out.println("in Book default constructor");
	}

	public Book(String isbn, String publisher, Date year, int edition, double price, String title, String category,
			int bookInStock) {
		super();
		this.isbn = isbn;
		this.publisher = publisher;
		this.year = year;
		this.edition = edition;
		this.price = price;
		this.title = title;
		this.category = category;
		this.bookInStock = bookInStock;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBookInStock() {
		return bookInStock;
	}

	public void setBookInStock(int bookInStock) {
		this.bookInStock = bookInStock;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] bs) {
		this.image = bs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void addAuthor(Author auth) {
		this.authors.add(auth);
		auth.setBook(this);
	}

	public void removeAuthor(Author auth) {
		this.authors.remove(auth);
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", isbn=" + isbn + ", publisher=" + publisher + ", year=" + year
				+ ", edition=" + edition + ", price=" + price + ", title=" + title + ", category=" + category
				+ ", bookInStock=" + bookInStock + ", image=" + Arrays.toString(image) + ", type=" + type + ", authors="
				+ authors + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Book book = (Book) o;

		return bookId.equals(book.bookId);
	}

	@Override
	public int hashCode() {
		return bookId.hashCode();
	}

}
