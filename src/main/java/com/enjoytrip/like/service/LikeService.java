package com.enjoytrip.like.service;

import com.enjoytrip.like.dto.LikeDto;
import com.enjoytrip.like.entity.CommentLike;
import com.enjoytrip.like.entity.PostLike;
import com.enjoytrip.like.entity.ProductLike;
import com.enjoytrip.like.mapper.LikeMapper;
import com.enjoytrip.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;


    public CommentLike createProductCommentLike(LikeDto.Post postRequest) {
        CommentLike commentLike = likeMapper.postRequestToProductCommentLike(postRequest);
        return likeRepository.save(commentLike);
    }

    public CommentLike createPostCommentLike(LikeDto.Post postRequest) {
        CommentLike commentLike = likeMapper.postRequestToPostCommentLike(postRequest);
        return likeRepository.save(commentLike);
    }

    public PostLike createPostLike(LikeDto.Post postRequest) {
        PostLike postLike = likeMapper.postRequestToPostLike(postRequest);
        return likeRepository.save(postLike);
    }

    public ProductLike createProductLike(LikeDto.Post postRequest) {
        ProductLike productLike = likeMapper.postRequestToProductLike(postRequest);
        return likeRepository.save(productLike);
    }

    public void cancelLike(LikeDto.Delete deleteRequest) {
        likeRepository.deleteById(deleteRequest.getLikeId());
    }

}
