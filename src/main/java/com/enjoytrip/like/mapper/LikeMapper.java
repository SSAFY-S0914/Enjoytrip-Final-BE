package com.enjoytrip.like.mapper;

import com.enjoytrip.like.dto.LikeDto;
import com.enjoytrip.like.entity.CommentLike;
import com.enjoytrip.like.entity.PostLike;
import com.enjoytrip.like.entity.ProductLike;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(source = "targetId", target = "productId")
    @Mapping(target = "member", ignore = true)
    ProductLike postRequestToProductLike(LikeDto.Post postRequest);

    @Mapping(target = "comment", ignore = true)
    @Mapping(target = "member", ignore = true)
    CommentLike postRequestToCommentLike(LikeDto.Post postRequest);

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "member", ignore = true)
    PostLike postRequestToPostLike(LikeDto.Post postRequest);
}
