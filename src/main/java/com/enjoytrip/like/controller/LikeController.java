package com.enjoytrip.like.controller;

import com.enjoytrip.like.dto.LikeDto;
import com.enjoytrip.like.entity.CommentLike;
import com.enjoytrip.like.entity.PostLike;
import com.enjoytrip.like.entity.ProductLike;
import com.enjoytrip.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/products")
    public ResponseEntity<?> createProductLike(@RequestBody LikeDto.Post postRequest) {
        ProductLike productLike = likeService.createProductLike(postRequest);

        URI location = createLocation(productLike.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/products")
    public ResponseEntity<?> cancelProductLike(@RequestBody LikeDto.Delete deleteRequest) {
        likeService.cancelLike(deleteRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/comments/product")
    public ResponseEntity<?> createProductCommentLike(@RequestBody LikeDto.Post postRequest) {
        CommentLike commentLike = likeService.createProductCommentLike(postRequest);

        URI location = createLocation(commentLike.getId());
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/comments/post")
    public ResponseEntity<?> createPostCommentLike(@RequestBody LikeDto.Post postRequest) {
        CommentLike commentLike = likeService.createPostCommentLike(postRequest);

        URI location = createLocation(commentLike.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/comments")
    public ResponseEntity<?> cancelCommentLike(@RequestBody LikeDto.Delete deleteRequest) {
        likeService.cancelLike(deleteRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPostLike(@RequestBody LikeDto.Post postRequest) {
        PostLike postLike = likeService.createPostLike(postRequest);

        URI location = createLocation(postLike.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/posts")
    public ResponseEntity<?> cancelPostLike(@RequestBody LikeDto.Delete deleteRequest) {
        likeService.cancelLike(deleteRequest);
        return ResponseEntity.noContent().build();
    }

    private static URI createLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
