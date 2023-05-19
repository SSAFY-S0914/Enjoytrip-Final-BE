package com.enjoytrip.comment.mapper;

import com.enjoytrip.comment.dto.PostCommentDto;
import com.enjoytrip.comment.dto.ProductCommentDto;
import com.enjoytrip.comment.entity.PostComment;
import com.enjoytrip.comment.entity.ProductComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "writer.nickname", target = "writer")
    @Mapping(source = "writer.id", target = "writerId")
    PostCommentDto.Get postCommentToGetRequest(PostComment postComment);

    List<PostCommentDto.Get> postCommentToGetRequest(List<PostComment> postComment);

    PostComment postRequestToPostComment(PostCommentDto.Post postRequest);

    void patchRequestToPostComment(PostCommentDto.Patch patchRequest, @MappingTarget PostComment postComment);

    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "writer.nickname", target = "writer")
    @Mapping(source = "writer.id", target = "writerId")
    ProductCommentDto.Get productCommentToGetRequest(ProductComment productComment);

    List<ProductCommentDto.Get> productCommentToGetRequest(List<ProductComment> productComment);

    ProductComment postRequestToProductComment(ProductCommentDto.Post postRequest);

    void patchRequestToProductComment(ProductCommentDto.Patch patchRequest, @MappingTarget ProductComment productComment);

}
