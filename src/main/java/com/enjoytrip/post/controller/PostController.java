package com.enjoytrip.post.controller;

import com.enjoytrip.post.dto.PostDto;
import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.mapper.PostMapper;
import com.enjoytrip.post.service.PostService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;


    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto.Post postRequest) {
    }

    @GetMapping("/{post-id}")
    public ResponseEntity findById(@PathVariable("post-id") Long postId) {

    }

    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") Long postId) {

    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") Long postId) {

    }

    @GetMapping("/public")
    public ResponseEntity findAllByPublic() {

    }

    @GetMapping("/group/{group-id}")
    public ResponseEntity findAllByGroupId(@PathVariable("group-id") Long groupId) {

    }

    @GetMapping("/group/member/{member-id}")
    public ResponseEntity findAllByMemberId(@PathVariable("member-id") Long memberId) {

    }

}
