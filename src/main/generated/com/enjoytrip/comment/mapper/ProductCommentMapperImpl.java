package com.enjoytrip.comment.mapper;

import com.enjoytrip.comment.dto.ProductCommentDto.Get;
import com.enjoytrip.comment.dto.ProductCommentDto.Patch;
import com.enjoytrip.comment.dto.ProductCommentDto.Post;
import com.enjoytrip.comment.entity.ProductComment;
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
public class ProductCommentMapperImpl extends ProductCommentMapper {

    @Override
    public Get productCommentToGetRequest(ProductComment productComment) {
        if ( productComment == null ) {
            return null;
        }

        Long commentId = null;
        String content = null;
        com.enjoytrip.member.dto.MemberDto.Get writer = null;
        Integer star = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        commentId = productComment.getId();
        content = productComment.getContent();
        writer = memberToGet( productComment.getWriter() );
        star = productComment.getStar();
        createdAt = productComment.getCreatedAt();
        modifiedAt = productComment.getModifiedAt();

        Get get = new Get( commentId, content, writer, star, createdAt, modifiedAt );

        return get;
    }

    @Override
    public List<Get> productCommentToGetRequest(List<ProductComment> productComment) {
        if ( productComment == null ) {
            return null;
        }

        List<Get> list = new ArrayList<Get>( productComment.size() );
        for ( ProductComment productComment1 : productComment ) {
            list.add( productCommentToGetRequest( productComment1 ) );
        }

        return list;
    }

    @Override
    public ProductComment postRequestToProductComment(Post postRequest, Long productId) {
        if ( postRequest == null && productId == null ) {
            return null;
        }

        ProductComment productComment = new ProductComment();

        if ( postRequest != null ) {
            productComment.setWriter( mapMember( postRequest.getWriterId() ) );
            productComment.setContent( postRequest.getContent() );
            productComment.setStar( postRequest.getStar() );
        }
        if ( productId != null ) {
            productComment.setProductId( productId );
        }

        return productComment;
    }

    @Override
    public void patchRequestToProductComment(Patch patchRequest, ProductComment productComment) {
        if ( patchRequest == null ) {
            return;
        }

        productComment.setContent( patchRequest.getContent() );
        productComment.setStar( patchRequest.getStar() );
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
