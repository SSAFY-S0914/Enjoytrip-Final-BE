package com.enjoytrip.comment.mapper;

import com.enjoytrip.comment.dto.ProductCommentDto;
import com.enjoytrip.comment.entity.ProductComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCommentMapper {

    @Mapping(source = "id", target = "commentId")
    ProductCommentDto.Get productCommentToGetRequest(ProductComment productComment);

    List<ProductCommentDto.Get> productCommentToGetRequest(List<ProductComment> productComment);

    ProductComment postRequestToProductComment(ProductCommentDto.Post postRequest);

    void patchRequestToProductComment(ProductCommentDto.Patch patchRequest, @MappingTarget ProductComment productComment);
}
