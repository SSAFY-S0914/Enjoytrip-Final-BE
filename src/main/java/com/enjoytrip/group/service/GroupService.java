package com.enjoytrip.group.service;

import com.enjoytrip.group.entity.Group;
import com.enjoytrip.group.mapper.GroupMapper;
import com.enjoytrip.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

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

    public void requestInvite(Long groupId, Long memberId) {

    }

    public void approveInvite(Long groupId, Long memberId) {

    }

}

