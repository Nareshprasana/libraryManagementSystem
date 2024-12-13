package com.example.libraryManagementSystem.service;

import com.example.libraryManagementSystem.entity.Borrow;
import com.example.libraryManagementSystem.exception.CustomException;
import com.example.libraryManagementSystem.repository.BorrowRepository;
import com.example.libraryManagementSystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    // Add a new borrowing record
    public void borrowBook(Borrow borrow) {
        // Check if the book is available
        int availableCopies = bookRepository.getAvailableCopies(borrow.getBookId());
        if (availableCopies > 0) {
            // Decrement the available copies of the book
            bookRepository.updateAvailableCopies(borrow.getBookId(), availableCopies - 1);
            // Save the borrow record
            borrowRepository.save(borrow);
        } else {
            throw new CustomException("The book is not available for borrowing.", "BOOK_NOT_AVAILABLE");
        }
    }

    // Return a borrowed book
    public void returnBook(int borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId);
        if (borrow != null) {
            // Increment the available copies of the book
            int availableCopies = bookRepository.getAvailableCopies(borrow.getBookId());
            bookRepository.updateAvailableCopies(borrow.getBookId(), availableCopies + 1);
            // Update the borrow record as returned
            borrowRepository.markAsReturned(borrowId);
        } else {
            throw new CustomException("Borrow record not found.", "BORROW_RECORD_NOT_FOUND");
        }
    }

    // Get all borrowing records
    public List<Borrow> getAllBorrowRecords() {
        return borrowRepository.findAll();
    }

    // Get borrowing record by ID
    public Borrow getBorrowById(int id) {
        return borrowRepository.findById(id);
    }

    // Get borrowing records with detailed book and member info
    public List<Borrow> getDetailedBorrowRecords() {
        return borrowRepository.findAllWithDetails();
    }

    public List<Borrow> getBorrowDetails() {
        return List.of();
    }
}
