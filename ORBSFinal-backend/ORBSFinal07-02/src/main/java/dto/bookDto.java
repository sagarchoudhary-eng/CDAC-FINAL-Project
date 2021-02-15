package dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class bookDto {

	private String isbn;
	private String publisher;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date year;
	private int edition;

	private double price;

	private String title;

	private int bookInStock;

//	private String name;
//   
//	private String email;

	private String category;

	public bookDto() {
		System.out.println("in boook dto");
	}
//	public bookDto(String isbn, String publisher, Date year, int edition, double price, String title, int bookInStock,
//			 String type, String category, String name, String email) {
//		super();
//		this.isbn = isbn;
//		this.publisher = publisher;
//		this.year = year;
//		this.edition = edition;
//		this.price = price;
//		this.title = title;
//		this.bookInStock = bookInStock;
//		this.category = category;
//		this.name = name;
//		this.email = email;
//	}

	public String getIsbn() {
		return isbn;
	}

	public bookDto(String isbn, String publisher, Date year, int edition, double price, String title, int bookInStock,
			String category) {
		super();
		this.isbn = isbn;
		this.publisher = publisher;
		this.year = year;
		this.edition = edition;
		this.price = price;
		this.title = title;
		this.bookInStock = bookInStock;
		this.category = category;

	}

	public String getPublisher() {
		return publisher;
	}

	public Date getYear() {
		return year;
	}

	public int getEdition() {
		return edition;
	}

	public double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public int getBookInStock() {
		return bookInStock;
	}

	@Override
	public String toString() {
		return "bookDto [isbn=" + isbn + ", publisher=" + publisher + ", year=" + year + ", edition=" + edition + "]";
	}

	public String getCategory() {
		return category;
//	}
//	public String getName() {
//		return name;
//	}
//
//	public String getEmail() {
//		return email;
//	}

//	@Override
//	public String toString() {
//		return "bookDto [isbn=" + isbn + ", publisher=" + publisher + ", year=" + year + ", edition=" + edition
//				+ ", price=" + price + ", title=" + title + ", bookInStock=" + bookInStock + ", name=" + name
//				+ ", email=" + email + ", categoryName=" + category + "]";
//	}

	}
}
