package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Book;
import com.app.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements IBookService {
	@Autowired
	private BookRepository book_repo;

	@Override
	public List<Book> getAllBooks() {

		return book_repo.findAll();
	}

	@Override
	public Book saveBookDetails(Book book) {

		return book_repo.save(book);
	}

	@Override
	public void updateStock(Book book) {

		book_repo.save(book);

	}

	@Override
	public Optional<Book> findBookByTitle(String title) {

		return book_repo.findByTitle(title);
	}

	@Override
	public String deleteById(int id) {

		String mesg = "Student record deleted successfully";
		book_repo.deleteById(id);
		return mesg;
	}

	@Override
	public Optional<Book> getBookDetailsById(int bookId) {

		return book_repo.findById(bookId);
	}

	@Override
	public Book updateDetails(Book b) {

		return book_repo.save(b);
	}

	@Override
	public Book updateBookDetails(Book b) {
		// chk if product exists
		Optional<Book> optional = book_repo.findById(b.getBookId());
		if (optional.isPresent())
			return book_repo.save(b);
		return null;
		// update
		// if product is not found : throw custom exception
		// throw new ProductNotFoundException("Product Not Found : Invalid Product id "
		// + p.getProductId());

	}

	// User End Service Methods IMPL.
	/*
	 * @Override public List<Book> findByTitle(String title) { return
	 * book_repo.findByTitle(title); }
	 */

	@Override
	public List<Book> findByCategory(String category) {
		return book_repo.findByCategory(category);
	}

	@Override
	public List<Book> findByPriceBetween(double min, double max) {
		return book_repo.findByPriceBetween(min, max);
	}

	@Override
	public Book findBookByBookId(Integer book_id) {
		return book_repo.findBookByBookId(book_id);
	}

}
