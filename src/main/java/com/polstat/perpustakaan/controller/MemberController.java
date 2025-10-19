package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.MemberDto;
import com.polstat.perpustakaan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 1. CREATE
    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.createMember(memberDto));
    }

    // 2. READ all members
    @GetMapping
    public ResponseEntity<List<MemberDto>> getMembers() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    // 3. READ member by ID
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    // 4. UPDATE a member
    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable("id") Long id, @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.updateMember(id, memberDto));
    }

    // 5. DELETE a member
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member with id " + id + " has been successfully deleted.");
    }
}