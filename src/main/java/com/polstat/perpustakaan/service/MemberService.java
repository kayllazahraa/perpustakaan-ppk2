package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    List<MemberDto> getMembers();
    MemberDto getMemberById(Long id);
    MemberDto updateMember(Long id, MemberDto memberDto);
    void deleteMember(Long id);
}