package com.enjoytrip.like.mapper;

import com.enjoytrip.like.dto.LikeDto.Post;
import com.enjoytrip.like.entity.CommentLike;
import com.enjoytrip.like.entity.PostLike;
import com.enjoytrip.like.entity.ProductLike;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-24T09:45:00+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
@Component
public class LikeMapperImpl extends LikeMapper {

    @Override
    public ProductLike postRequestToProductLike(Post postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        ProductLike productLike = new ProductLike();

        productLike.setProductId( postRequest.getTargetId() );
        productLike.setMember( mapMember( postRequest.getMemberId() ) );

        return productLike;
    }

    @Override
    public CommentLike postRequestToProductCommentLike(Post postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        CommentLike commentLike = new CommentLike();

        commentLike.setMember( mapMember( postRequest.getMemberId() ) );
        commentLike.setComment( mapProductComment( postRequest.getTargetId() ) );

        return commentLike;
    }

    @Override
    public CommentLike postRequestToPostCommentLike(Post postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        CommentLike commentLike = new CommentLike();

        commentLike.setMember( mapMember( postRequest.getMemberId() ) );
        commentLike.setComment( mapPostComment( postRequest.getTargetId() ) );

        return commentLike;
    }

    @Override
    public PostLike postRequestToPostLike(Post postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        PostLike postLike = new PostLike();

        postLike.setMember( mapMember( postRequest.getMemberId() ) );
        postLike.setPost( mapPost( postRequest.getTargetId() ) );

        return postLike;
    }
}
