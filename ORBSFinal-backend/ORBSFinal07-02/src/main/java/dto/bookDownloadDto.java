package dto;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

public class bookDownloadDto {
	private Integer bookId;
	private String isbn;
	private String publisher;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date year;
	private int edition;

	private double price;

	private String title;

	private int bookInStock;
	private String category;

	@Lob
	private byte[] image;

	private String type;

	public bookDownloadDto() {
		// TODO Auto-generated constructor stub
	}

	public bookDownloadDto(Integer bookId, String isbn, String publisher, Date year, int edition, double price,
			String title, int bookInStock, String category, byte[] image, String type) {
		super();
		this.bookId = bookId;
		this.isbn = isbn;
		this.publisher = publisher;
		this.year = year;
		this.edition = edition;
		this.price = price;
		this.title = title;
		this.bookInStock = bookInStock;
		this.category = category;
		this.image = image;
		this.type = type;
	}

	public Integer getBookId() {
		return bookId;
	}

	public String getIsbn() {
		return isbn;
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

	public String getCategory() {
		return category;
	}

	public byte[] getImage() {
		return image;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "bookDownloadDto [bookId=" + bookId + ", isbn=" + isbn + ", publisher=" + publisher + ", year=" + year
				+ ", edition=" + edition + ", price=" + price + ", title=" + title + ", bookInStock=" + bookInStock
				+ ", category=" + category + ", image=" + Arrays.toString(image) + ", type=" + type + "]";
	}

//public byte[] getImage() {
//	return image;
//}
//
//
//
//
//public String getType() {
//	return type;
//}

}
