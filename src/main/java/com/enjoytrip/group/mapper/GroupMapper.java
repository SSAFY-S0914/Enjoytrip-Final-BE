package com.enjoytrip.group.mapper;

import com.enjoytrip.group.dto.GroupDto;
import com.enjoytrip.group.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDto.Get groupToGetRequest(Group group);

    List<GroupDto.Get> groupListToGetRequest(List<Group> group);

    @Mapping(target = "id", ignore = true)
    Group postRequestToGroup(GroupDto.Post groupDto);
    
    @Mapping(target = "id", ignore = true)
    Group patchRequestToGroup(GroupDto.Patch groupDto);
}
