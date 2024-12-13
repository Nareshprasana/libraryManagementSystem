package com.example.libraryManagementSystem.entity;

import java.time.LocalDate;

public class Borrow {
    private int id;
    private int memberId;
    private int bookId;
    private LocalDate borrowedDate;
    private LocalDate dueDate;

    // Constructors
    public Borrow() {
    }

    public Borrow(int id, int memberId, int bookId, LocalDate borrowedDate, LocalDate dueDate) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", borrowedDate=" + borrowedDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
