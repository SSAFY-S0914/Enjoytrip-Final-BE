package com.enjoytrip.comment.controller;

import com.enjoytrip.comment.dto.PostCommentDto;
import com.enjoytrip.comment.dto.ProductCommentDto;
import com.enjoytrip.comment.entity.PostComment;
import com.enjoytrip.comment.entity.ProductComment;
import com.enjoytrip.comment.mapper.PostCommentMapper;
import com.enjoytrip.comment.mapper.ProductCommentMapper;
import com.enjoytrip.comment.service.CommentService;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final PostCommentMapper postCommentMapper;
    private final ProductCommentMapper productCommentMapper;
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/products/{product-id}")
    public ResponseEntity findAllProductComment(@PathVariable("product-id") Long productId) {
        List<ProductComment> productCommentList = commentService.findAllByProductId(productId);
        List<ProductCommentDto.Get> results = productCommentMapper.productCommentToGetRequest(productCommentList);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/products/{product-id}")
    public ResponseEntity createNewProductComment(@PathVariable("product-id") Long productId, @RequestBody ProductCommentDto.Post postRequest) {
        Member member = memberService.findOneMember(postRequest.getWriterId());
        ProductComment productComment = productCommentMapper.postRequestToProductComment(postRequest);
        productComment.setWriter(member);
        productComment.setProductId(productId);
        commentService.createProductComment(productComment);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/products/{product-id}/{comment-id}")
    public ResponseEntity updateProductComment(@PathVariable("product-id") Long productId,
                                               @PathVariable("comment-id") Long commentId,
                                               @RequestBody ProductCommentDto.Patch patchRequest) {
        ProductComment productComment = commentService.findProductCommentById(commentId);
        commentService.updateProductComment(productComment, patchRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{product-id}/{comment-id}")
    public ResponseEntity deleteProductComment(@PathVariable("product-id") Long productId,
                                               @PathVariable("comment-id") Long commentId) {
        commentService.deleteProductCommentById(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/{post-id}")
    public ResponseEntity findAllPostComment(@PathVariable("post-id") Long postId) {
        List<PostComment> postCommentList = commentService.findAllByPostId(postId);
        List<PostCommentDto.Get> results = postCommentMapper.postCommentToGetRequest(postCommentList);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/posts/{post-id}")
    public ResponseEntity createNewPostComment(@PathVariable("post-id") Long postId, @RequestBody PostCommentDto.Post postRequest) {
        Member member = memberService.findOneMember(postRequest.getWriterId());
        Post post = postService.findById(postId);
        PostComment postComment = postCommentMapper.postRequestToPostComment(postRequest);
        postComment.setWriter(member);
        postComment.setPost(post);
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

    @GetMapping("/comments/members/{member-id}/products")
    public ResponseEntity findAllProductCommentByMemberId(@PathVariable("member-id") Long memberId) {
        List<ProductComment> productCommentList = commentService.findAllProductCommentByMemberId(memberId);
        List<ProductCommentDto.Get> results = productCommentMapper.productCommentToGetRequest(productCommentList);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/comments/members/{member-id}/posts")
    public ResponseEntity findAllPostCommentByMemberId(@PathVariable("member-id") Long memberId) {
        List<PostComment> postCommentList = commentService.findAllPostCommentByMemberId(memberId);
        List<PostCommentDto.Get> results = postCommentMapper.postCommentToGetRequest(postCommentList);
        return ResponseEntity.ok(results);
    }
}
