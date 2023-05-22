package com.enjoytrip.comment.controller;

import com.enjoytrip.comment.dto.PostCommentDto;
import com.enjoytrip.comment.entity.PostComment;
import com.enjoytrip.comment.mapper.PostCommentMapper;
import com.enjoytrip.comment.service.CommentService;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class PostCommentController {

    private final CommentService commentService;
    private final PostCommentMapper postCommentMapper;
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/posts/{post-id}")
    public ResponseEntity findAllPostComment(@PathVariable("post-id") Long postId) {
        List<PostComment> postCommentList = commentService.findAllByPostId(postId);
        List<PostCommentDto.Get> results = postCommentMapper.postCommentToGetRequest(postCommentList);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/posts/{post-id}")
    public ResponseEntity createNewPostComment(@PathVariable("post-id") Long postId, @RequestBody PostCommentDto.Post postRequest) {
        PostComment postComment = postCommentMapper.postRequestToPostComment(postRequest, postId);
        commentService.createPostComment(postComment);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/posts/{post-id}/{comment-id}")
    public ResponseEntity updatePostComment(@PathVariable("post-id") Long postId,
                                            @PathVariable("comment-id") Long commentId,
                                            @RequestBody PostCommentDto.Patch patchRequest) {
        PostComment postComment = commentService.findPostCommentById(commentId);
        commentService.updatePostComment(postComment, patchRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{post-id}/{comment-id}")
    public ResponseEntity deletePostComment(@PathVariable("post-id") Long postId,
                                            @PathVariable("comment-id") Long commentId) {
        commentService.deletePostCommentById(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/members/{member-id}/posts")
    public ResponseEntity findAllPostCommentByMemberId(@PathVariable("member-id") Long memberId) {
        List<PostComment> postCommentList = commentService.findAllPostCommentByMemberId(memberId);
        List<PostCommentDto.Get> results = postCommentMapper.postCommentToGetRequest(postCommentList);
        return ResponseEntity.ok(results);
    }
}
