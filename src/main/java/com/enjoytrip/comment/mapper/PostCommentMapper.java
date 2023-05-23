package com.enjoytrip.comment.mapper;

import com.enjoytrip.comment.dto.PostCommentDto;
import com.enjoytrip.comment.entity.PostComment;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.service.PostService;
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
    private final PostService postService;

    @Mapping(source = "id", target = "commentId")
    public abstract PostCommentDto.Get postCommentToGetRequest(PostComment postComment);

    public abstract List<PostCommentDto.Get> postCommentToGetRequest(List<PostComment> postComment);

    @Mapping(target = "writer", source = "postRequest.writerId", qualifiedByName = "mapMember")
    @Mapping(target = "post", source = "postId", qualifiedByName = "mapPost")
    public abstract PostComment postRequestToPostComment(PostCommentDto.Post postRequest, Long postId);

    public abstract void patchRequestToPostComment(PostCommentDto.Patch patchRequest, @MappingTarget PostComment postComment);


    @Named("mapMember")
    protected Member mapMember(Long memberId) {
        return memberService.findOneMember(memberId);
    }

    @Named("mapPost")
    protected Post mapPost(Long postId) {
        return postService.findById(postId);
    }

}
