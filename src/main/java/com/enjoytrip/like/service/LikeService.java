package com.enjoytrip.like.service;

import com.enjoytrip.comment.service.CommentService;
import com.enjoytrip.like.dto.LikeDto;
import com.enjoytrip.like.entity.CommentLike;
import com.enjoytrip.like.entity.Like;
import com.enjoytrip.like.entity.PostLike;
import com.enjoytrip.like.entity.ProductLike;
import com.enjoytrip.like.mapper.LikeMapper;
import com.enjoytrip.like.repository.LikeRepository;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final MemberService memberService;
    private final CommentService commentService;
    private final PostService postService;

    public void likeProduct(LikeDto.Post postRequest) {
        Long memberId = postRequest.getMemberId();
        Member member = memberService.findOneMember(memberId);

        ProductLike productLike = likeMapper.postRequestToProductLike(postRequest);
        productLike.setMember(member);

        likeRepository.save(productLike);
    }

    public void likeComment(LikeDto.Post postRequest) {
        Long memberId = postRequest.getMemberId();
        Member member = memberService.findOneMember(memberId);

        CommentLike commentLike = likeMapper.postRequestToCommentLike(postRequest);
        commentLike.setMember(member);

        likeRepository.save(commentLike);
    }

    public void likePost(LikeDto.Post postRequest) {
        Long memberId = postRequest.getMemberId();
        Member member = memberService.findOneMember(memberId);
        Long postId = postRequest.getTargetId();
        Post post = postService.findById(postId);

        PostLike postLike = likeMapper.postRequestToPostLike(postRequest);
        postLike.setMember(member);
        postLike.setPost(post);

        likeRepository.save(postLike);
    }

    public void cancelLikeProduct(LikeDto.Delete deleteRequest) {
        Like like = likeRepository.findById(deleteRequest.getLikeId()).get();
        likeRepository.delete(like);
    }

    public void cancelLikeComment(LikeDto.Delete deleteRequest) {
        Like like = likeRepository.findById(deleteRequest.getLikeId()).get();
        likeRepository.delete(like);
    }

    public void cancelLikePost(LikeDto.Delete deleteRequest) {
        Like like = likeRepository.findById(deleteRequest.getLikeId()).get();
        likeRepository.delete(like);
    }

}
