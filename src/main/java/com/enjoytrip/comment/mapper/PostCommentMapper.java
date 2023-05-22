package com.enjoytrip.comment.mapper;

import com.enjoytrip.comment.dto.PostCommentDto;
import com.enjoytrip.comment.entity.PostComment;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PostCommentMapper {

    private final MemberService memberService;

    @Mapping(source = "id", target = "commentId")
    public abstract PostCommentDto.Get postCommentToGetRequest(PostComment postComment);

    public abstract List<PostCommentDto.Get> postCommentToGetRequest(List<PostComment> postComment);

    @Mapping(target = "writer", source = "postRequest.writerId", qualifiedByName = "mapMember")
    public abstract PostComment postRequestToPostComment(PostCommentDto.Post postRequest);

    public abstract void patchRequestToPostComment(PostCommentDto.Patch patchRequest, @MappingTarget PostComment postComment);


    @Named("mapMember")
    protected Member mapMember(Long memberId) {
        return memberService.findOneMember(memberId);
    }

}
