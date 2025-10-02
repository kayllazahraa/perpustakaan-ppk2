package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.MemberDto;
import com.polstat.perpustakaan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MemberGraphqlController {

    @Autowired
    private MemberService memberService;

    @QueryMapping
    public List<MemberDto> members() {
        return memberService.getMembers();
    }

    @QueryMapping
    public MemberDto memberById(@Argument Long id) {
        return memberService.getMemberById(id);
    }

    @MutationMapping
    public MemberDto createMember(@Argument String memberId, @Argument String name, @Argument String address, @Argument String phoneNumber) {
        MemberDto memberDto = MemberDto.builder()
                .memberId(memberId)
                .name(name)
                .address(address)
                .phone_number(phoneNumber)
                .build();
        return memberService.createMember(memberDto);
    }

    @MutationMapping
    public MemberDto updateMember(@Argument Long id, @Argument String memberId, @Argument String name, @Argument String address, @Argument String phoneNumber) {
        MemberDto memberDto = MemberDto.builder()
                .memberId(memberId)
                .name(name)
                .address(address)
                .phone_number(phoneNumber)
                .build();
        return memberService.updateMember(id, memberDto);
    }

    @MutationMapping
    public MemberDto deleteMember(@Argument Long id) {
        MemberDto memberToDelete = memberService.getMemberById(id);
        memberService.deleteMember(id);
        return memberToDelete;
    }
}