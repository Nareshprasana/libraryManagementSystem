package com.example.libraryManagementSystem.controller;

import com.example.libraryManagementSystem.entity.Borrow;
import com.example.libraryManagementSystem.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // Create a new borrow record (lend a book to a member)
    @PostMapping
    public ResponseEntity<String> borrowBook(@RequestBody Borrow borrow) {
        borrowService.borrowBook(borrow);
        return ResponseEntity.ok("Book borrowed successfully.");
    }

    // Get all borrow records
    @GetMapping
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAllBorrowRecords();
        return ResponseEntity.ok(borrows);
    }

    // Get a specific borrow record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable int id) {
        Borrow borrow = borrowService.getBorrowById(id);
        return ResponseEntity.ok(borrow);
    }

    // Return a borrowed book
    @PutMapping("/{id}/return")
    public ResponseEntity<String> returnBook(@PathVariable int id) {
        borrowService.returnBook(id);
        return ResponseEntity.ok("Book returned successfully.");
    }

    // Get borrow records with book and member details
    @GetMapping("/details")
    public ResponseEntity<List<Borrow>> getBorrowDetails() {
        List<Borrow> borrowDetails = borrowService.getDetailedBorrowRecords();
        return ResponseEntity.ok(borrowDetails);
    }
}
