package com.enjoytrip.group.mapper;

import com.enjoytrip.group.dto.GroupDto;
import com.enjoytrip.group.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(source = "id", target = "groupId")
    GroupDto.Get groupToGetRequest(Group group);

    List<GroupDto.Get> groupListToGetRequest(List<Group> group);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groupMemberList", ignore = true)
    Group postRequestToGroup(GroupDto.Post postRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groupMemberList", ignore = true)
    Group patchRequestToGroup(GroupDto.Patch patchRequest, @MappingTarget Group group);
}
