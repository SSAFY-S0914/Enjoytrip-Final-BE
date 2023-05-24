package com.enjoytrip.group.mapper;

import com.enjoytrip.group.dto.GroupDto.Get;
import com.enjoytrip.group.dto.GroupDto.Patch;
import com.enjoytrip.group.dto.GroupDto.Post;
import com.enjoytrip.group.entity.Group;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-24T09:36:04+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public Get groupToGetRequest(Group group) {
        if ( group == null ) {
            return null;
        }

        Long groupId = null;
        String name = null;
        String description = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        groupId = group.getId();
        name = group.getName();
        description = group.getDescription();
        createdAt = group.getCreatedAt();
        modifiedAt = group.getModifiedAt();

        Get get = new Get( groupId, name, description, createdAt, modifiedAt );

        return get;
    }

    @Override
    public List<Get> groupListToGetRequest(List<Group> group) {
        if ( group == null ) {
            return null;
        }

        List<Get> list = new ArrayList<Get>( group.size() );
        for ( Group group1 : group ) {
            list.add( groupToGetRequest( group1 ) );
        }

        return list;
    }

    @Override
    public Group postRequestToGroup(Post postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        Group group = new Group();

        group.setName( postRequest.getName() );
        group.setDescription( postRequest.getDescription() );

        return group;
    }

    @Override
    public Group patchRequestToGroup(Patch patchRequest, Group group) {
        if ( patchRequest == null ) {
            return null;
        }

        group.setName( patchRequest.getName() );
        group.setDescription( patchRequest.getDescription() );

        return group;
    }
}
