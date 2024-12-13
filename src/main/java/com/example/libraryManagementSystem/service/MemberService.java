package com.example.libraryManagementSystem.service;

import com.example.libraryManagementSystem.entity.Member;
import com.example.libraryManagementSystem.exception.CustomException;
import com.example.libraryManagementSystem.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Add a new member
    public void addMember(Member member) {
        memberRepository.save(member);
    }

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Get a member by ID
    public Member getMemberById(int id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new CustomException("Member not found with id: " + id, "MEMBER_NOT_FOUND"));
    }

    // Update an existing member
    public void updateMember(int id, Member member) {
        if (memberRepository.findById(id).isPresent()) {
            member.setId(id); // Ensure the ID is consistent
            memberRepository.update(member);
        } else {
            throw new CustomException("Cannot update. Member not found with id: " + id, "MEMBER_NOT_FOUND");
        }
    }

    // Delete a member by ID
    public void deleteMemberById(int id) {
        if (memberRepository.findById(id).isPresent()) {
            memberRepository.deleteById(id);
        } else {
            throw new CustomException("Cannot delete. Member not found with id: " + id, "MEMBER_NOT_FOUND");
        }
    }
}
