package com.enjoytrip.post.mapper;

import com.enjoytrip.post.dto.PostDto;
import com.enjoytrip.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postRequestToPost(PostDto.Post postRequest);
}
