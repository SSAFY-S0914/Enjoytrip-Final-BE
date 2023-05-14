package com.enjoytrip.post.mapper;

import com.enjoytrip.post.dto.PostDto;
import com.enjoytrip.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postRequestToPost(PostDto.Post postRequest);

    Post patchRequestToPost(PostDto.Patch patchRequest);

    @Mapping(source = "post.writer.nickname", target = "nickname")
    PostDto.Get postToGetRequest(Post post);

    List<PostDto.Get> postListToGetRequest(List<Post> posts);
}
