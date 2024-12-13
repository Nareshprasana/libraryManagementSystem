package com.example.libraryManagementSystem.controller;

import com.example.libraryManagementSystem.entity.Member;
import com.example.libraryManagementSystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Add a new member
    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody Member member) {
        memberService.addMember(member);
        return ResponseEntity.ok("Member added successfully.");
    }

    // Get all members
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // Get a member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    // Update a member
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable int id, @RequestBody Member member) {
        memberService.updateMember(id, member); // Pass both the ID and the updated Member object
        return ResponseEntity.ok("Member updated successfully.");
    }

    // Delete a member
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable int id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.ok("Member deleted successfully.");
    }
}
