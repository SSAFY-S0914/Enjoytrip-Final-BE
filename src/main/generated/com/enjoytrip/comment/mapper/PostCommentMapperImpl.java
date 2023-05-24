package com.enjoytrip.comment.mapper;

import com.enjoytrip.comment.dto.PostCommentDto.Get;
import com.enjoytrip.comment.dto.PostCommentDto.Patch;
import com.enjoytrip.comment.dto.PostCommentDto.Post;
import com.enjoytrip.comment.entity.PostComment;
import com.enjoytrip.member.entity.Member;
import java.time.LocalDateTime;
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
public class PostCommentMapperImpl extends PostCommentMapper {

    @Override
    public Get postCommentToGetRequest(PostComment postComment) {
        if ( postComment == null ) {
            return null;
        }

        String content = null;
        com.enjoytrip.member.dto.MemberDto.Get writer = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        content = postComment.getContent();
        writer = memberToGet( postComment.getWriter() );
        createdAt = postComment.getCreatedAt();
        modifiedAt = postComment.getModifiedAt();

        Long commentId = null;

        Get get = new Get( commentId, content, writer, createdAt, modifiedAt );

        return get;
    }

    @Override
    public List<Get> postCommentToGetRequest(List<PostComment> postComment) {
        if ( postComment == null ) {
            return null;
        }

        List<Get> list = new ArrayList<Get>( postComment.size() );
        for ( PostComment postComment1 : postComment ) {
            list.add( postCommentToGetRequest( postComment1 ) );
        }

        return list;
    }

    @Override
    public PostComment postRequestToPostComment(Post postRequest, Long postId) {
        if ( postRequest == null && postId == null ) {
            return null;
        }

        PostComment postComment = new PostComment();

        if ( postRequest != null ) {
            postComment.setWriter( mapMember( postRequest.getWriterId() ) );
            postComment.setContent( postRequest.getContent() );
        }
        if ( postId != null ) {
            postComment.setPost( mapPost( postId ) );
        }

        return postComment;
    }

    @Override
    public void patchRequestToPostComment(Patch patchRequest, PostComment postComment) {
        if ( patchRequest == null ) {
            return;
        }

        postComment.setContent( patchRequest.getContent() );
    }

    protected com.enjoytrip.member.dto.MemberDto.Get memberToGet(Member member) {
        if ( member == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String password = null;
        String email = null;
        String nickname = null;
        String phoneNum = null;
        String birth = null;
        String profileImage = null;

        id = member.getId();
        name = member.getName();
        password = member.getPassword();
        email = member.getEmail();
        nickname = member.getNickname();
        phoneNum = member.getPhoneNum();
        if ( member.getBirth() != null ) {
            birth = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( member.getBirth() );
        }
        profileImage = member.getProfileImage();

        com.enjoytrip.member.dto.MemberDto.Get get = new com.enjoytrip.member.dto.MemberDto.Get( id, name, password, email, nickname, phoneNum, birth, profileImage );

        return get;
    }
}
