package com.rohit.library;

import com.rohit.library.model.Patron;
import com.rohit.library.pattern.factory.BookFactory;
import com.rohit.library.pattern.strategy.SearchByAuthor;
import com.rohit.library.pattern.strategy.SearchByIsbn;
import com.rohit.library.pattern.strategy.SearchByTitle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
		Library library = new Library();

		// Add Books
		library.addBook(BookFactory.createBook("ISBN101", "Java Basics", "James Gosling", 1995));
		library.addBook(BookFactory.createBook("ISBN102", "Spring Boot Guide", "Rod Johnson", 2015));
		library.addBook(BookFactory.createBook("ISBN103", "Clean Code", "Robert Martin", 2008));

		// Add Patrons
		library.addPatron(new Patron("P1", "Rohit", "rohit@gmail.com"));
		library.addPatron(new Patron("P2", "Amit", "amit@gmail.com"));

		// Search Books
		System.out.println("Search by Title: " + library.searchBooks(new SearchByTitle(), "Java"));
		System.out.println("Search by Author: " + library.searchBooks(new SearchByAuthor(), "Martin"));
		System.out.println("Search by ISBN: " + library.searchBooks(new SearchByIsbn(), "ISBN102"));

		// Checkout
		var loan = library.checkoutBook("ISBN101", "P1");

		// Inventory
		System.out.println("Available Books: " + library.getAvailableBooks());
		System.out.println("Borrowed Books: " + library.getBorrowedBooks());

		// Return
		library.returnBook(loan.getLoanId());

		System.out.println("Available Books after return: " + library.getAvailableBooks());
	}

}
