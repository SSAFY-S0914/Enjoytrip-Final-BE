package com.enjoytrip.like.controller;

import com.enjoytrip.like.dto.LikeDto;
import com.enjoytrip.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/products")
    public ResponseEntity likeProduct(@RequestBody LikeDto.Post postRequest) {
        likeService.likeProduct(postRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products")
    public ResponseEntity cancelLikeProduct(@RequestBody LikeDto.Delete deleteRequest) {
        likeService.cancelLikeProduct(deleteRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments")
    public ResponseEntity likeComment(@RequestBody LikeDto.Post postRequest) {
        likeService.likeComment(postRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products")
    public ResponseEntity cancelLikeComment(@RequestBody LikeDto.Delete deleteRequest) {
        likeService.cancelLikeComment(deleteRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/posts")
    public ResponseEntity likePost(@RequestBody LikeDto.Post postRequest) {
        likeService.likePost(postRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products")
    public ResponseEntity cancelLikePost(@RequestBody LikeDto.Delete deleteRequest) {
        likeService.cancelLikePost(deleteRequest);
        return ResponseEntity.ok().build();
    }

}
