package com.enjoytrip.post.controller;

import com.enjoytrip.post.dto.PostDto;
import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.mapper.PostMapper;
import com.enjoytrip.post.service.PostService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;


    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto.Post postRequest) {
        Post post = postMapper.postRequestToPost(postRequest);
        postService.save(post);
        return new ResponseEntity(CREATED);
    }

    @GetMapping("/{post-id}")
    public ResponseEntity findById(@PathVariable("post-id") Long postId) {
        Post post = postService.findById(postId);
        PostDto.Get result = postMapper.postToGetRequest(post);
        return new ResponseEntity(new SingleResponseDto<>(result), OK);
    }

    @PatchMapping()
    public ResponseEntity patchPost(@RequestBody PostDto.Patch patchRequest) {
        Post post = postMapper.patchRequestToPost(patchRequest);
        postService.save(post);
        return new ResponseEntity(CREATED);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") Long postId) {
        postService.deleteById(postId);
        return new ResponseEntity(ACCEPTED);
    }

    @GetMapping("/public")
    public ResponseEntity findAllByPublic() {
        List<Post> posts = postService.findAllByPublic();
        List<PostDto.Get> results = postMapper.postListToGetRequest(posts);
        return new ResponseEntity(new SingleResponseDto<>(results), OK);
    }

    @GetMapping("/group/{group-id}")
    public ResponseEntity findAllByGroupId(@PathVariable("group-id") Long groupId) {
        List<Post> posts = postService.findAllByGroupId(groupId);
        List<PostDto.Get> results = postMapper.postListToGetRequest(posts);
        return new ResponseEntity(new SingleResponseDto<>(results), OK);
    }

    @GetMapping("/group/member/{member-id}")
    public ResponseEntity findAllByMemberId(@PathVariable("member-id") Long memberId) {
        List<Post> posts = postService.findAllByMemberId(memberId);
        List<PostDto.Get> results = postMapper.postListToGetRequest(posts);
        return new ResponseEntity(new SingleResponseDto<>(results), OK);
    }

}
