package com.enjoytrip.member.controller;

import com.enjoytrip.auth.dto.LoginDto;
import com.enjoytrip.member.dto.MemberDto;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.mapper.MemberMapper;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.utils.dtoUtils.MultiResponseDto;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin("*")
@RestController
@RequestMapping("/members")
@Validated
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final MemberMapper mapper;


    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post postRequest) {
        Member memberForService = mapper.memberPostToMember(postRequest);
        Member memberForResponse = service.createMember(memberForService);
        MemberDto.Get response = mapper.memberToMemberResponse(memberForResponse);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@Valid @RequestBody MemberDto.Patch patchRequest,
            @Positive @PathVariable("member-id") long memberId) {
        Member memberForService = mapper.memberPatchToMember(patchRequest);
        memberForService.setId(memberId);
        Member memberForResponse = service.updateMember(memberForService);
        MemberDto.Get response = mapper.memberToMemberResponse(memberForResponse);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getOneMember(@PathVariable("member-id") long memberId) {
        Member memberForResponse = service.findOneMember(memberId);
        MemberDto.Get response = mapper.memberToMemberResponse(memberForResponse);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllMembers(@Positive @RequestParam int page,
            @Positive @RequestParam int size) {
        Page<Member> pageMember = service.findAllMembers(page - 1, size);
        List<Member> memberListForResponse = pageMember.getContent();
        List<MemberDto.Get> response = mapper.membersToMemberList(memberListForResponse);

        return new ResponseEntity(new MultiResponseDto<>(response, pageMember), HttpStatus.OK);
    }

    //TODO 회원 상태 변경 로직
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteOneMember(@PathVariable("member-id") long memberId) {
        service.deleteOneMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findPass/{email}")
    public ResponseEntity getOneMemberByEmail(@PathVariable("email") String email) {
        Member memberForResponse = service.findByEmail(email);
        MemberDto.Get response = mapper.memberToMemberResponse(memberForResponse);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/changePass")
    public ResponseEntity changePassword(@RequestBody LoginDto loginDto) {
        service.changPassword(loginDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
