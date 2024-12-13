package com.example.libraryManagementSystem.controller;

import com.example.libraryManagementSystem.service.BookService;
import com.example.libraryManagementSystem.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Add a book
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
            return ResponseEntity.ok("Book added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while adding the book.");
        }
    }


    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book book) {
        try {
            bookService.updateBook(id, book);
            return ResponseEntity.ok("Book updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while updating the book.");
        }
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting the book.");
        }
    }

    // Search books by title, author, or ISBN
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn) {
        List<Book> books = bookService.searchBooks(title, author, isbn);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }
}
