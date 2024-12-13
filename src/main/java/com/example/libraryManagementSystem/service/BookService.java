package com.example.libraryManagementSystem.service;

import com.example.libraryManagementSystem.entity.Book;
import com.example.libraryManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public void updateBook(int id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublishedDate(updatedBook.getPublishedDate());
            book.setAvailableCopies(updatedBook.getAvailableCopies());
            bookRepository.update(book);
        }
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String title, String author, String isbn) {
        return bookRepository.searchBooks(title, author, isbn);
    }
}
