package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Book;
import com.app.service.IBookService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class BookControllerUser {
//	@Value("${file.upload.location}")
//	private String location;

	@Autowired
	private IBookService bookService;

	public BookControllerUser() {
		System.out.println("In Book Controller For User constrctor...");
	}

	@GetMapping(value = "/books") // api : http://localhost:8080/user/books
	public ResponseEntity<?> showAllBooks() {
		System.out.println("in showAllBooks method");

		List<Book> bookList = bookService.getAllBooks();
		if (bookList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(bookList, HttpStatus.OK);
	}

	@GetMapping(value = "/title/{title}") // api : http://localhost:8080/user/title/{_title_}
	public ResponseEntity<?> showBookByName(@PathVariable String title) {
		Optional<Book> bookByName = bookService.findBookByTitle(title);

		if (bookByName.isPresent()) {
			Book book = bookByName.get();
			return new ResponseEntity<>(book, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/category/{category}") // api : http://localhost:8080/user/category/{_cat_}
	public ResponseEntity<?> showBookByCategory(@PathVariable String category) {
		List<Book> bookByCat = bookService.findByCategory(category);

		if (!bookByCat.isEmpty()) {
			// Book book = bookByCat.get();
			return new ResponseEntity<>(bookByCat, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/price") // api : http://localhost:8080/user/price?min=value&max=value
	public ResponseEntity<?> showBookByPriceRange(@RequestParam double min, @RequestParam double max) {

		List<Book> bookByPriceRange = bookService.findByPriceBetween(min, max);

		if (!bookByPriceRange.isEmpty()) {
			// Book book = bookByPriceRange.get();
			return new ResponseEntity<>(bookByPriceRange, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/*
	 * @GetMapping(value = "/book/image/{book_id}") public ResponseEntity<?>
	 * showBookImages (@PathVariable(name = "book_id") Integer bookId) throws
	 * IOException { Optional<Book> boo = bookService.findBookById(bookId); // Path
	 * path = Paths.get(location, imgName); }
	 */
}
