package com.rohit.library.pattern.strategy;

import com.rohit.library.model.Book;
import java.util.List;

public interface SearchStrategy {
    List<Book> search(List<Book> books, String keyword);
}
