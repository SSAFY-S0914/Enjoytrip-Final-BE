//package com.enjoytrip.post.mapper;
//
//import com.enjoytrip.post.dto.PostDto;
//import com.enjoytrip.post.entity.Post;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface PostMapper {
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "writer", ignore = true)
//    @Mapping(target = "group", ignore = true)
//    Post postRequestToPost(PostDto.Post postRequest);
//
//    @Mapping(target = "writer", ignore = true)
//    @Mapping(target = "group", ignore = true)
//    Post patchRequestToPost(PostDto.Patch patchRequest);
//
//    @Mapping(source = "post.writer.nickname", target = "nickname")
//    PostDto.Get postToGetRequest(Post post);
//
//    List<PostDto.Get> postListToGetRequest(List<Post> posts);
//}
