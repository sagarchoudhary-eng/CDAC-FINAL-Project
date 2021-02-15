package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Author;
import com.app.pojos.Book;
import com.app.repository.AuthorRepository;
import com.app.repository.BookRepository;

@Service
@Transactional
public class AuthorServiceImpl implements IAuthorService {
	@Autowired
	private AuthorRepository auth_repo;
	@Autowired
	private BookRepository book_repo;

	@Override

	public Author addAuthor(int bookId, Author auth) {
		List<Author> authors = new ArrayList<>();
		Book book1 = new Book();
		Optional<Book> Book = book_repo.findById(bookId);

		Book oldBook = Book.get();
		auth.setBook(oldBook);
		Author auth2 = auth_repo.save(auth);
//	        authors.add(auth2);
//	        book1.setAuthor(authors);

		return auth2;
	}

}
