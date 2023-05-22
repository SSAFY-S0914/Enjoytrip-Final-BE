package com.enjoytrip.comment.controller;

import com.enjoytrip.comment.dto.ProductCommentDto;
import com.enjoytrip.comment.entity.ProductComment;
import com.enjoytrip.comment.mapper.ProductCommentMapper;
import com.enjoytrip.comment.service.CommentService;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class ProductCommentController {

    private final CommentService commentService;
    private final ProductCommentMapper productCommentMapper;
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

    @GetMapping("/members/{member-id}/products")
    public ResponseEntity findAllProductCommentByMemberId(@PathVariable("member-id") Long memberId) {
        List<ProductComment> productCommentList = commentService.findAllProductCommentByMemberId(memberId);
        List<ProductCommentDto.Get> results = productCommentMapper.productCommentToGetRequest(productCommentList);
        return ResponseEntity.ok(results);
    }
}
