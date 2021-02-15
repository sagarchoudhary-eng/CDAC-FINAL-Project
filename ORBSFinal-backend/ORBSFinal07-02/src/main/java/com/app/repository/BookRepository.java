package com.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	Optional<Book> findByTitle(String title);

	// USER END METHODS

	// List<Book> findByTitle(String title); // Finding Book by name or Title.

	List<Book> findByCategory(String category); // Finding Book by Category.

	List<Book> findByPriceBetween(double min, double max); // Finding Book Range w.r.t. Price

	Book findBookByBookId(Integer book_id);

	// Book findOne(Integer bookId);

	// void save(Set<Book> keySet);
}
