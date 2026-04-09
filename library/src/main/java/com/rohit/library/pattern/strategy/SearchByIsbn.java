package com.rohit.library.pattern.strategy;

import com.rohit.library.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByIsbn implements SearchStrategy {

    @Override
    public List<Book> search(List<Book> books, String keyword) {
        return books.stream()
                .filter(b -> b.getIsbn().equalsIgnoreCase(keyword))
                .collect(Collectors.toList());
    }
}
