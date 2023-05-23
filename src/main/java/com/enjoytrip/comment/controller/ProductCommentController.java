package com.enjoytrip.comment.controller;

import com.enjoytrip.comment.dto.ProductCommentDto;
import com.enjoytrip.comment.entity.Comment;
import com.enjoytrip.comment.entity.ProductComment;
import com.enjoytrip.comment.mapper.ProductCommentMapper;
import com.enjoytrip.comment.service.CommentService;
import com.enjoytrip.member.service.MemberService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/comments")
public class ProductCommentController {

    @Qualifier(value = "ProductCommentService")
    private final CommentService commentService;
    private final ProductCommentMapper productCommentMapper;
    private final MemberService memberService;

    @GetMapping("/products/{product-id}")
    public ResponseEntity<SingleResponseDto<List<ProductCommentDto.Get>>> findAllProductComment(@PathVariable("product-id") Long productId) {
        List<ProductComment> productCommentList = (List<ProductComment>) commentService.findAllByTargetId(productId);
        List<ProductCommentDto.Get> results = productCommentMapper.productCommentToGetRequest(productCommentList);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @PostMapping("/products/{product-id}")
    public ResponseEntity<?> createNewProductComment(@PathVariable("product-id") Long productId,
                                                     @RequestBody ProductCommentDto.Post postRequest) {
        ProductComment productComment = productCommentMapper.postRequestToProductComment(postRequest, productId);
        Comment saved = commentService.createNewComment(productComment);

        URI location = createLocation(saved);
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/products/{product-id}/{comment-id}")
    public ResponseEntity<?> updateProductComment(@PathVariable("product-id") Long productId,
                                                  @PathVariable("comment-id") Long commentId,
                                                  @RequestBody ProductCommentDto.Patch patchRequest) {
        Comment saved = commentService.updateComment(patchRequest, commentId);

        URI location = createLocation(saved);
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/products/{product-id}/{comment-id}")
    public ResponseEntity<?> deleteProductComment(@PathVariable("product-id") Long productId,
                                                  @PathVariable("comment-id") Long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/members/{member-id}/products")
    public ResponseEntity<SingleResponseDto<List<ProductCommentDto.Get>>> findAllProductCommentByMemberId(@PathVariable("member-id") Long memberId) {
        List<ProductComment> productCommentList = (List<ProductComment>) commentService.findAllByMemberId(memberId);
        List<ProductCommentDto.Get> results = productCommentMapper.productCommentToGetRequest(productCommentList);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    private static URI createLocation(Comment saved) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
    }
}
