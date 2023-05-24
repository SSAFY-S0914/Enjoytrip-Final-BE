package com.enjoytrip.post.mapper;

import com.enjoytrip.group.dto.GroupDto.Get;
import com.enjoytrip.post.dto.PostDto.Patch;
import com.enjoytrip.post.entity.Post;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-24T09:36:04+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public com.enjoytrip.post.dto.PostDto.Get postToGetRequest(Post post) {
        if ( post == null ) {
            return null;
        }

        String title = null;
        String content = null;
        String createdAt = null;
        String modifiedAt = null;

        title = post.getTitle();
        content = post.getContent();
        if ( post.getCreatedAt() != null ) {
            createdAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( post.getCreatedAt() );
        }
        if ( post.getModifiedAt() != null ) {
            modifiedAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( post.getModifiedAt() );
        }

        com.enjoytrip.member.dto.MemberDto.Get memberDto = null;
        Get groupDto = null;

        com.enjoytrip.post.dto.PostDto.Get get = new com.enjoytrip.post.dto.PostDto.Get( memberDto, groupDto, title, content, createdAt, modifiedAt );

        return get;
    }

    @Override
    public List<com.enjoytrip.post.dto.PostDto.Get> postListToGetRequest(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<com.enjoytrip.post.dto.PostDto.Get> list = new ArrayList<com.enjoytrip.post.dto.PostDto.Get>( posts.size() );
        for ( Post post : posts ) {
            list.add( postToGetRequest( post ) );
        }

        return list;
    }

    @Override
    public Post postRequestToPost(com.enjoytrip.post.dto.PostDto.Post postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        Post post = new Post();

        post.setWriter( mapMember( postRequest.getWriterId() ) );
        post.setGroup( mapGroup( postRequest.getGroupId() ) );
        post.setTitle( postRequest.getTitle() );
        post.setContent( postRequest.getContent() );
        post.setScope( postRequest.getScope() );

        return post;
    }

    @Override
    public void patchRequestToPost(Patch patchRequest, Post post) {
        if ( patchRequest == null ) {
            return;
        }

        post.setWriter( mapMember( patchRequest.getWriterId() ) );
        post.setGroup( mapGroup( patchRequest.getGroupId() ) );
        post.setId( patchRequest.getId() );
        post.setTitle( patchRequest.getTitle() );
        post.setContent( patchRequest.getContent() );
        post.setScope( patchRequest.getScope() );
    }
}
