package com.rohit.library;

import com.rohit.library.model.*;
import com.rohit.library.pattern.strategy.SearchStrategy;
import com.rohit.library.util.LibraryLogger;

import java.util.*;
import java.util.logging.Logger;

public class Library {

    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Patron> patrons = new HashMap<>();
    private final Map<String, Loan> loans = new HashMap<>();

    private final Logger logger = LibraryLogger.getLogger(Library.class);

    // ---------------- BOOK MANAGEMENT ----------------

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
        logger.info("Book added: " + book.getIsbn());
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
        logger.info("Book removed: " + isbn);
    }

    public void updateBook(String isbn, String title, String author, int year) {
        Book book = getBookByIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(year);
        logger.info("Book updated: " + isbn);
    }

    public Book getBookByIsbn(String isbn) {
        Book book = books.get(isbn);
        if (book == null) throw new RuntimeException("Book not found: " + isbn);
        return book;
    }

    public List<Book> searchBooks(SearchStrategy strategy, String keyword) {
        return strategy.search(new ArrayList<>(books.values()), keyword);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    // ---------------- PATRON MANAGEMENT ----------------

    public void addPatron(Patron patron) {
        patrons.put(patron.getPatronId(), patron);
        logger.info("Patron added: " + patron.getPatronId());
    }

    public void updatePatron(String patronId, String name, String email) {
        Patron patron = getPatronById(patronId);
        patron.setName(name);
        patron.setEmail(email);
        logger.info("Patron updated: " + patronId);
    }

    public Patron getPatronById(String patronId) {
        Patron patron = patrons.get(patronId);
        if (patron == null) throw new RuntimeException("Patron not found: " + patronId);
        return patron;
    }

    // ---------------- LENDING PROCESS ----------------

    public Loan checkoutBook(String isbn, String patronId) {

        Book book = getBookByIsbn(isbn);

        if (book.getStatus() == BookStatus.BORROWED) {
            throw new RuntimeException("Book already borrowed: " + isbn);
        }

        Patron patron = getPatronById(patronId);

        book.setStatus(BookStatus.BORROWED);

        String loanId = UUID.randomUUID().toString();
        Loan loan = new Loan(loanId, isbn, patronId);

        loans.put(loanId, loan);
        patron.addLoan(loan);

        logger.info("Book checked out: " + isbn + " by Patron: " + patronId);

        return loan;
    }

    public void returnBook(String loanId) {

        Loan loan = loans.get(loanId);
        if (loan == null) throw new RuntimeException("Loan not found: " + loanId);

        if (loan.isReturned()) {
            throw new RuntimeException("Book already returned for loan: " + loanId);
        }

        loan.markReturned();

        Book book = getBookByIsbn(loan.getIsbn());
        book.setStatus(BookStatus.AVAILABLE);

        logger.info("Book returned: " + book.getIsbn());
    }

    // ---------------- INVENTORY ----------------

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getStatus() == BookStatus.AVAILABLE) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getStatus() == BookStatus.BORROWED) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans.values());
    }
}
