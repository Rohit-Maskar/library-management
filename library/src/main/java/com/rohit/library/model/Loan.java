package com.rohit.library.model;

import java.time.LocalDateTime;

public class Loan {

    private final String loanId;
    private final String isbn;
    private final String patronId;
    private final LocalDateTime checkoutTime;
    private LocalDateTime returnTime;

    public Loan(String loanId, String isbn, String patronId) {
        this.loanId = loanId;
        this.isbn = isbn;
        this.patronId = patronId;
        this.checkoutTime = LocalDateTime.now();
    }

    public String getLoanId() { return loanId; }
    public String getIsbn() { return isbn; }
    public String getPatronId() { return patronId; }
    public LocalDateTime getCheckoutTime() { return checkoutTime; }
    public LocalDateTime getReturnTime() { return returnTime; }

    public boolean isReturned() {
        return returnTime != null;
    }

    public void markReturned() {
        this.returnTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Loan{id='" + loanId + "', isbn='" + isbn + "', patron='" + patronId +
                "', checkout=" + checkoutTime + ", return=" + returnTime + "}";
    }
}
