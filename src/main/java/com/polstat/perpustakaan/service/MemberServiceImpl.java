package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.MemberDto;
import com.polstat.perpustakaan.entity.Member;
import com.polstat.perpustakaan.mapper.MemberMapper;
import com.polstat.perpustakaan.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = memberRepository.save(MemberMapper.mapToMember(memberDto));
        return MemberMapper.mapToMemberDto(member);
    }

    @Override
    public List<MemberDto> getMembers() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(MemberMapper::mapToMemberDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        return MemberMapper.mapToMemberDto(member);
    }

    @Override
    public MemberDto updateMember(Long id, MemberDto memberDto) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        existingMember.setName(memberDto.getName());
        existingMember.setAddress(memberDto.getAddress());
        existingMember.setPhone_number(memberDto.getPhone_number());
        Member updatedMember = memberRepository.save(existingMember);
        return MemberMapper.mapToMemberDto(updatedMember);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        memberRepository.deleteById(id);
    }
}