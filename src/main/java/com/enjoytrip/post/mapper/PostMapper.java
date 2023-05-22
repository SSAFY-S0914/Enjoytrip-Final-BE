package com.enjoytrip.post.mapper;

import com.enjoytrip.group.entity.Group;
import com.enjoytrip.group.service.GroupService;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.post.dto.PostDto;
import com.enjoytrip.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PostMapper {

    private final MemberService memberService;
    private final GroupService groupService;

    public abstract PostDto.Get postToGetRequest(Post post);

    public abstract List<PostDto.Get> postListToGetRequest(List<Post> posts);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writer", source = "postRequest.writerId", qualifiedByName = "mapMember")
    @Mapping(target = "group", source = "postRequest.groupId", qualifiedByName = "mapGroup")
    public abstract Post postRequestToPost(PostDto.Post postRequest);

    @Mapping(target = "writer", source = "patchRequest.writerId", qualifiedByName = "mapMember")
    @Mapping(target = "group", source = "patchRequest.groupId", qualifiedByName = "mapGroup")
    public abstract void patchRequestToPost(PostDto.Patch patchRequest, @MappingTarget Post post);

    @Named("mapMember")
    protected Member mapMember(Long writerId) {
        return memberService.findOneMember(writerId);
    }

    @Named("mapGroup")
    protected Group mapGroup(Long groupId) {
        return groupService.findById(groupId);
    }

}
