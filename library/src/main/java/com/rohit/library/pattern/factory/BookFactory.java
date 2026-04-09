package com.rohit.library.pattern.factory;

import com.rohit.library.model.Book;

public class BookFactory {

    public static Book createBook(String isbn, String title, String author, int year) {
        return new Book(isbn, title, author, year);
    }
}
