package com.rohit.library.model;

import java.util.ArrayList;
import java.util.List;

public class Patron {

    private final String patronId;
    private String name;
    private String email;

    private final List<Loan> borrowingHistory = new ArrayList<>();

    public Patron(String patronId, String name, String email) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
    }

    public String getPatronId() { return patronId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public List<Loan> getBorrowingHistory() {
        return new ArrayList<>(borrowingHistory);
    }

    public void addLoan(Loan loan) {
        borrowingHistory.add(loan);
    }

    @Override
    public String toString() {
        return "Patron{id='" + patronId + "', name='" + name + "', email='" + email + "'}";
    }
}
