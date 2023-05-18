package com.enjoytrip.group.service;

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

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final GroupMemberRepository groupMemberRepository;
    private final MemberService memberService;

    public List<Group> findAll() {
        return groupRepository.findAll();
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

    public void deleteMemberFromGroup(Long groupId, Long memberId) {
        Group group = findById(groupId);
        Member member = memberService.findOneMember(memberId);
        GroupMember groupMember = group.getGroupMember(memberId);

        group.deleteGroupMember(groupMember);
        //TODO: member 쪽에서도 삭제 시켜주기

        groupRepository.save(group);
        memberService.updateMember(member);
        groupMemberRepository.delete(groupMember);
    }

    public void requestInvite(Long groupId, Long memberId) {
        Group group = findById(groupId);
        Member member = memberService.findOneMember(memberId);
        GroupMember groupMember = GroupMember.createGroupMember(group, member, false);

        groupRepository.save(group);
        memberService.updateMember(member);
        groupMemberRepository.save(groupMember);
    }

    public void approveInvite(Long groupId, Long memberId) {
        Group group = findById(groupId);
        GroupMember groupMember = group.getGroupMember(memberId);
        groupMember.setStatus(RequestStatus.APPROVED);
    }

}

