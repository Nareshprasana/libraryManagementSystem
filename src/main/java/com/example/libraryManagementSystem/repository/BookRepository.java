package com.example.libraryManagementSystem.repository;

import com.example.libraryManagementSystem.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Book objects
    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn"),
                    rs.getObject("published_date", LocalDate.class),
                    rs.getInt("available_copies")
            );
        }
    }

    // Create a new book
    public int save(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, published_date, available_copies) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublishedDate(), book.getAvailableCopies());
    }

    // Get all books
    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    // Get a book by ID
    public Optional<Book> findById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        List<Book> books = jdbcTemplate.query(sql, new BookRowMapper(), id);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }

    // Update a book
    public int update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, published_date = ?, available_copies = ? WHERE id = ?";
        return jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublishedDate(), book.getAvailableCopies(), book.getId());
    }

    // Delete a book by ID
    public int deleteById(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Search books by title, author, or ISBN
    public List<Book> searchBooks(String title, String author, String isbn) {
        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? OR isbn LIKE ?";
        String titleKeyword = "%" + (title == null ? "" : title.toLowerCase()) + "%";
        String authorKeyword = "%" + (author == null ? "" : author.toLowerCase()) + "%";
        String isbnKeyword = "%" + (isbn == null ? "" : isbn) + "%";
        return jdbcTemplate.query(sql, new BookRowMapper(), titleKeyword, authorKeyword, isbnKeyword);
    }

    // Get the number of available copies of a book
    public int getAvailableCopies(int bookId) {
        String sql = "SELECT available_copies FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
    }

    // Update the available copies of a book
    public int updateAvailableCopies(int bookId, int availableCopies) {
        String sql = "UPDATE books SET available_copies = ? WHERE id = ?";
        return jdbcTemplate.update(sql, availableCopies, bookId);
    }
}
