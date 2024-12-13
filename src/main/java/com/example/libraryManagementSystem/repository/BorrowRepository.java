package com.example.libraryManagementSystem.repository;

import com.example.libraryManagementSystem.entity.Borrow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BorrowRepository {
    private final JdbcTemplate jdbcTemplate;

    public BorrowRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Borrow objects
    private static class BorrowRowMapper implements RowMapper<Borrow> {
        @Override
        public Borrow mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Borrow(
                    rs.getInt("id"),
                    rs.getInt("member_id"),
                    rs.getInt("book_id"),
                    rs.getObject("borrowed_date", LocalDate.class),
                    rs.getObject("due_date", LocalDate.class)
            );
        }
    }

    // Create a new borrow record
    public int save(Borrow borrow) {
        String sql = "INSERT INTO borrows (member_id, book_id, borrowed_date, due_date) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, borrow.getMemberId(), borrow.getBookId(), borrow.getBorrowedDate(), borrow.getDueDate());
    }

    // Get all borrow records
    public List<Borrow> findAll() {
        String sql = "SELECT * FROM borrows";
        return jdbcTemplate.query(sql, new BorrowRowMapper());
    }

    // Get a borrow record by ID
    public Borrow findById(int id) {
        String sql = "SELECT * FROM borrows WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BorrowRowMapper(), id);
    }

    // Mark a borrow record as returned
    public int markAsReturned(int id) {
        String sql = "UPDATE borrows SET returned = TRUE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Delete a borrow record by ID
    public int deleteById(int id) {
        String sql = "DELETE FROM borrows WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Join Borrow with Member and Book details
    public List<Borrow> findAllWithDetails() {
        String sql = """
            SELECT b.id, b.member_id, b.book_id, b.borrowed_date, b.due_date, 
                   m.name AS member_name, k.title AS book_title 
            FROM borrows b 
            JOIN members m ON b.member_id = m.id 
            JOIN books k ON b.book_id = k.id
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Borrow(
                rs.getInt("id"),
                rs.getInt("member_id"),
                rs.getInt("book_id"),
                rs.getObject("borrowed_date", LocalDate.class),
                rs.getObject("due_date", LocalDate.class)
        ));
    }
}
