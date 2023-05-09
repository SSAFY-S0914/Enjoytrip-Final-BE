package com.enjoytrip.member.controller;

import com.enjoytrip.member.dto.MemberDto;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.mapper.MemberMapper;
import com.enjoytrip.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private MemberService service;
    private MemberMapper mapper;

    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post post) {
        Member serviceMember = mapper.memberPostToMember(post);
        Member responseMember = service.createMember(serviceMember);
        MemberDto.Response response = mapper.memberToMemberResponse(responseMember);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
