package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojos.Book;

public interface IBookService {
	List<Book> getAllBooks();

	Book saveBookDetails(Book transientBook);

	void updateStock(Book book);

	Optional<Book> getBookDetailsById(int bookId);

	String deleteById(int id);

	Book updateDetails(Book b);

	Optional<Book> findBookByTitle(String title);

	Book updateBookDetails(Book b);

	// User End
	// List<Book> findByTitle(String title);

	List<Book> findByCategory(String category);

	List<Book> findByPriceBetween(double min, double max);

	Book findBookByBookId(Integer book_id);
}
