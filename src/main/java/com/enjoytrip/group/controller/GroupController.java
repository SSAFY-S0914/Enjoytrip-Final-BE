package com.enjoytrip.group.controller;

import com.enjoytrip.group.dto.GroupDto;
import com.enjoytrip.group.entity.Group;
import com.enjoytrip.group.mapper.GroupMapper;
import com.enjoytrip.group.service.GroupService;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity findAll() {
        List<Group> results = groupService.findAll();
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @PostMapping
    public ResponseEntity createNewGroup(@RequestBody GroupDto.Post postRequest) {
        Group group = groupMapper.postRequestToGroup(postRequest);
        Member member = memberService.findOneMember(postRequest.getMemberId());
        groupService.createNewGroup(group, member);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{group-id}")
    public ResponseEntity findById(@PathVariable("group-id") Long groupId) {
        Group result = groupService.findById(groupId);
        return ResponseEntity.ok(new SingleResponseDto<>(result));
    }

    @PatchMapping("/{group-id}")
    public ResponseEntity updateGroup(@PathVariable("group-id") Long groupId, @RequestBody GroupDto.Patch patchRequest) {
        Group group = groupService.findById(groupId);
        groupService.updateGroup(group, patchRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{group-id}")
    public ResponseEntity deleteGroup(@PathVariable("group-id") Long groupId) {
        groupService.deleteById(groupId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{group-id}/members")
    public ResponseEntity findAllMembers(@PathVariable("group-id") Long groupId) {
        List<Member> results = groupService.findAllMembers(groupId);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/{group-id}/request")
    public ResponseEntity findAllRequests(@PathVariable("group-id") Long groupId) {
        List<Member> results = groupService.findAllRequests(groupId);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @PostMapping("/request")
    public ResponseEntity requestInvite(@RequestParam("groupId") Long groupId, @RequestParam("memberId") Long memberId) {
        groupService.requestInvite(groupId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approve")
    public ResponseEntity approveInvite(@RequestParam("groupId") Long groupId, @RequestParam("memberId") Long memberId) {
        groupService.approveInvite(groupId, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{group-id}/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("group-id") Long groupId, @PathVariable("member-id") Long memberId) {
        groupService.deleteMemberFromGroup(groupId, memberId);
        return ResponseEntity.accepted().build();
    }

}
