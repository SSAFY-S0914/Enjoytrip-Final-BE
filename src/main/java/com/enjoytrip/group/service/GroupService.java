package com.enjoytrip.group.service;

import com.enjoytrip.group.dto.GroupDto;
import com.enjoytrip.group.entity.Group;
import com.enjoytrip.group.entity.GroupMember;
import com.enjoytrip.group.entity.RequestStatus;
import com.enjoytrip.group.mapper.GroupMapper;
import com.enjoytrip.group.repository.GroupMemberRepository;
import com.enjoytrip.group.repository.GroupRepository;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupMapper groupMapper;
    private final MemberService memberService;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public void createNewGroup(Group group, Member member) {
        GroupMember groupMember = GroupMember.createGroupMember(group, member, true);

        save(group);
        memberService.updateMember(member);
        groupMemberRepository.save(groupMember);
    }

    public void updateGroup(Group group, GroupDto.Patch patchRequest) {
        groupMapper.patchRequestToGroup(patchRequest, group);
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public Group findById(Long groupId) {
        return groupRepository.findById(groupId).get();
    }

    public void deleteById(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public List<Member> findAllMembers(Long groupId) {
        return getMemberList(groupId, RequestStatus.APPROVED);
    }

    public List<Member> findAllRequests(Long groupId) {
        return getMemberList(groupId, RequestStatus.REQUESTED);
    }

    public void deleteMemberFromGroup(Long groupId, Long memberId) {
        Group group = findById(groupId);
        Member member = memberService.findOneMember(memberId);
        GroupMember groupMember = group.getGroupMember(memberId);

        group.deleteGroupMember(groupMember);
        //TODO: member 쪽에서도 삭제 시켜주기

        save(group);
        memberService.updateMember(member);
        groupMemberRepository.delete(groupMember);
    }

    public void requestInvite(Long groupId, Long memberId) {
        Group group = findById(groupId);
        Member member = memberService.findOneMember(memberId);
        GroupMember groupMember = GroupMember.createGroupMember(group, member, false);

        save(group);
        memberService.updateMember(member);
        groupMemberRepository.save(groupMember);
    }

    public void approveInvite(Long groupId, Long memberId) {
        Group group = findById(groupId);
        GroupMember groupMember = group.getGroupMember(memberId);
        groupMember.setStatus(RequestStatus.APPROVED);
    }

    private List<Member> getMemberList(Long groupId, RequestStatus requested) {
        List<Member> list = new ArrayList<>();
        Group group = findById(groupId);
        for (GroupMember groupMember : group.getGroupMemberList()) {
            if (groupMember.getStatus().equals(requested)) {
                list.add(groupMember.getMember());
            }
        }
        return list;
    }


}

