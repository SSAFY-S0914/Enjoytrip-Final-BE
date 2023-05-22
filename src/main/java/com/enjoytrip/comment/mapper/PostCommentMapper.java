package com.enjoytrip.comment.mapper;

import com.enjoytrip.comment.dto.PostCommentDto;
import com.enjoytrip.comment.entity.PostComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostCommentMapper {

    @Mapping(source = "id", target = "commentId")
    PostCommentDto.Get postCommentToGetRequest(PostComment postComment);

    List<PostCommentDto.Get> postCommentToGetRequest(List<PostComment> postComment);

    PostComment postRequestToPostComment(PostCommentDto.Post postRequest);

    void patchRequestToPostComment(PostCommentDto.Patch patchRequest, @MappingTarget PostComment postComment);
}
