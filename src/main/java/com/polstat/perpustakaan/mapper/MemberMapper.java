package com.polstat.perpustakaan.mapper;

import com.polstat.perpustakaan.dto.MemberDto;
import com.polstat.perpustakaan.entity.Member;

public class MemberMapper {

    public static MemberDto mapToMemberDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .name(member.getName())
                .address(member.getAddress())
                .phone_number(member.getPhone_number())
                .build();
    }

    public static Member mapToMember(MemberDto memberDto) {
        return Member.builder()
                .id(memberDto.getId())
                .memberId(memberDto.getMemberId())
                .name(memberDto.getName())
                .address(memberDto.getAddress())
                .phone_number(memberDto.getPhone_number())
                .build();
    }
}