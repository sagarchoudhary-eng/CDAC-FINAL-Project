package com.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Author;
import com.app.pojos.Book;
import com.app.service.IAuthorService;
import com.app.service.IBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.bookDownloadDto;
import dto.bookDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/book")
public class BookControllerAdmin {

	@Autowired
	private IBookService book_service;
	@Autowired
	private IAuthorService author_service;

	public BookControllerAdmin() {
		System.out.println("In Book Controller For Admin constructor...");
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<?> getAllBooks() {
		System.out.println("in getAllStudent method");

		List<bookDownloadDto> dto = new ArrayList<>();
		List<Book> books = book_service.getAllBooks();
		if (books.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		for (Book book : books) {
			bookDownloadDto dwnldBook = new bookDownloadDto(book.getBookId(), book.getIsbn(), book.getPublisher(),
					book.getYear(), book.getEdition(), book.getPrice(), book.getTitle(), book.getBookInStock(),
					book.getCategory(), book.getImage(), book.getType());

			dto.add(dwnldBook);
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> saveBookDetails(@RequestParam String detail, @RequestParam MultipartFile imageFile) {
		System.out.println("data " + detail + " " + imageFile.getOriginalFilename());

		try {

			bookDto data = new ObjectMapper().readValue(detail, bookDto.class);
			if (data == null)
				return new ResponseEntity<>("Book data not submitted", HttpStatus.NO_CONTENT);
			Optional<Book> optionalBook = book_service.findBookByTitle(data.getTitle());
			System.out.println(optionalBook);
			if (!optionalBook.isPresent()) {

				Book book = new Book(data.getIsbn(), data.getPublisher(), data.getYear(), data.getEdition(),
						data.getPrice(), data.getTitle(), data.getCategory(), data.getBookInStock());

				book.setImage(imageFile.getBytes());
				book.setType(imageFile.getContentType());
				book_service.saveBookDetails(book);

				// Author author=new Author(data.getName(),data.getEmail());

//				List<Author>arth=new ArrayList<>(data.getAuth());
//				for (Author author : arth) {
//				book_service.saveBookDetails(book,author);
//				}
				return new ResponseEntity<>("File uploaded :" + imageFile.getOriginalFilename(), HttpStatus.OK);

			}
			Book existingBook = optionalBook.get();
			existingBook.setBookInStock(existingBook.getBookInStock() + data.getBookInStock());
			book_service.updateStock(existingBook);
			return new ResponseEntity<>("Book already existed in stock count increased", HttpStatus.FOUND);

		} catch (RuntimeException | IOException e) {
			System.out.println("err in save" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/author/{book_id}")
	public ResponseEntity<?> saveAuthorDetails(@PathVariable int book_id, @RequestBody Author a) {
		System.out.println("in save book details" + a);
		System.out.println("in save book details" + book_id);
		try {
			Author auth = author_service.addAuthor(book_id, a);
			return new ResponseEntity<>(auth, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in save" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		String mesg = book_service.deleteById(id);
		return new ResponseEntity<>(mesg, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findBookById(@PathVariable int id) {
		Optional<Book> b1 = book_service.getBookDetailsById(id);

		if (b1.isPresent()) {
			Book book = new Book();
			book = b1.get();
			bookDownloadDto dwnldBook = new bookDownloadDto(book.getBookId(), book.getIsbn(), book.getPublisher(),
					book.getYear(), book.getEdition(), book.getPrice(), book.getTitle(), book.getBookInStock(),
					book.getCategory(), book.getImage(), book.getType());
			System.out.println(dwnldBook);
			return new ResponseEntity<>(dwnldBook, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBookDetails(@RequestBody Book updat_book, @PathVariable int id) {

		try {

			Optional<Book> bookOptional = book_service.getBookDetailsById(id);
			// book_service.updateDetails(b);
			if (!bookOptional.isPresent())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			updat_book.setBookId(id);
			Book afterUpdt = book_service.updateDetails(updat_book);

			return new ResponseEntity<>(afterUpdt, HttpStatus.OK);

			// return new ResponseEntity<>(b, HttpStatus.OK);

		} catch (RuntimeException e) {
			System.out.println("err in update " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping
	public ResponseEntity<?> updateProductDetails(@RequestBody Book b) {
		System.out.println("in update product " + b);
		try {
			return ResponseEntity.ok(book_service.updateBookDetails(b));
		} catch (RuntimeException e) {
			System.out.println("err in controller " + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

//@GetMapping("/category")
//public ResponseEntity<?> getAllCategory(){
//	List<Category> cat=cat_service.getCategoryDetails();
//	if(cat.isEmpty())
//		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		return new ResponseEntity<>(cat,HttpStatus.OK);
//}
}
