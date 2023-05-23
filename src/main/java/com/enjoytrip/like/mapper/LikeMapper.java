package com.enjoytrip.like.mapper;

import com.enjoytrip.comment.entity.Comment;
import com.enjoytrip.comment.service.CommentService;
import com.enjoytrip.like.dto.LikeDto;
import com.enjoytrip.like.entity.CommentLike;
import com.enjoytrip.like.entity.PostLike;
import com.enjoytrip.like.entity.ProductLike;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LikeMapper {

    @Autowired
    private MemberService memberService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;

    @Mapping(target = "productId", source = "targetId")
    @Mapping(target = "member", source = "postRequest.memberId", qualifiedByName = "mapMember")
    public abstract ProductLike postRequestToProductLike(LikeDto.Post postRequest);

    @Mapping(target = "member", source = "postRequest.memberId", qualifiedByName = "mapMember")
    @Mapping(target = "comment", source = "postRequest.targetId", qualifiedByName = "mapComment")
    public abstract CommentLike postRequestToCommentLike(LikeDto.Post postRequest);

    @Mapping(target = "member", source = "postRequest.memberId", qualifiedByName = "mapMember")
    @Mapping(target = "post", source = "targetId", qualifiedByName = "mapPost")
    public abstract PostLike postRequestToPostLike(LikeDto.Post postRequest);

    @Named("mapMember")
    protected Member mapMember(Long memberId) {
        return memberService.findOneMember(memberId);
    }

    @Named("mapComment")
    protected Comment mapComment(Long targetId) {
        return commentService.findById(targetId);
    }

    @Named("mapPost")
    protected Post mapPost(Long targetId) {
        return postService.findById(targetId);
    }

}
