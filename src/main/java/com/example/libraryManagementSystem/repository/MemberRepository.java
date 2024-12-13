package com.example.libraryManagementSystem.repository;

import com.example.libraryManagementSystem.entity.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Member objects
    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getObject("registered_date", LocalDate.class)
            );
        }
    }

    // Create a new member
    public int save(Member member) {
        String sql = "INSERT INTO members (name, phone, registered_date) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, member.getName(), member.getPhone(), member.getRegisteredDate());
    }

    // Get all members
    public List<Member> findAll() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }

    // Get a member by ID
    public Optional<Member> findById(int id) {
        String sql = "SELECT * FROM members WHERE id = ?";
        List<Member> members = jdbcTemplate.query(sql, new MemberRowMapper(), id);
        if (members.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(members.get(0));
        }
    }

    // Update a member
    public int update(Member member) {
        String sql = "UPDATE members SET name = ?, phone = ?, registered_date = ? WHERE id = ?";
        return jdbcTemplate.update(sql, member.getName(), member.getPhone(), member.getRegisteredDate(), member.getId());
    }

    // Delete a member by ID
    public int deleteById(int id) {
        String sql = "DELETE FROM members WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
