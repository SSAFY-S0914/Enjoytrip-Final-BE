package com.enjoytrip.comment.controller;

import com.enjoytrip.comment.dto.PostCommentDto;
import com.enjoytrip.comment.entity.Comment;
import com.enjoytrip.comment.entity.PostComment;
import com.enjoytrip.comment.mapper.PostCommentMapper;
import com.enjoytrip.comment.service.PostCommentService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class PostCommentController {

    private final PostCommentService commentService;
    private final PostCommentMapper postCommentMapper;

    @GetMapping("/posts/{post-id}")
    public ResponseEntity<SingleResponseDto<List<PostCommentDto.Get>>> findAllPostComment(@PathVariable("post-id") Long postId) {
        List<PostComment> postCommentList = (List<PostComment>) commentService.findAllByMemberId(postId);
        List<PostCommentDto.Get> results = postCommentMapper.postCommentToGetRequest(postCommentList);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @PostMapping("/posts/{post-id}")
    public ResponseEntity<?> createNewPostComment(@PathVariable("post-id") Long postId,
                                                  @RequestBody PostCommentDto.Post postRequest) {
        PostComment postComment = postCommentMapper.postRequestToPostComment(postRequest, postId);
        Comment saved = commentService.createNewComment(postComment);

        URI location = createLocation(saved);
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/posts/{post-id}/{comment-id}")
    public ResponseEntity<?> updatePostComment(@PathVariable("post-id") Long postId,
                                               @PathVariable("comment-id") Long commentId,
                                               @RequestBody PostCommentDto.Patch patchRequest) {
        Comment saved = commentService.updateComment(patchRequest, commentId);

        URI location = createLocation(saved);
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/posts/{post-id}/{comment-id}")
    public ResponseEntity<?> deletePostComment(@PathVariable("post-id") Long postId,
                                               @PathVariable("comment-id") Long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/members/{member-id}/posts")
    public ResponseEntity<SingleResponseDto<List<PostCommentDto.Get>>> findAllPostCommentByMemberId(@PathVariable("member-id") Long memberId) {
        List<PostComment> postCommentList = (List<PostComment>) commentService.findAllByMemberId(memberId);
        List<PostCommentDto.Get> results = postCommentMapper.postCommentToGetRequest(postCommentList);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    private static URI createLocation(Comment saved) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
    }
}
