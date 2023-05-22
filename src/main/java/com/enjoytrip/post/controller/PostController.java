package com.enjoytrip.post.controller;

import com.enjoytrip.post.dto.PostDto;
import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.mapper.PostMapper;
import com.enjoytrip.post.service.PostService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/public")
    public ResponseEntity<SingleResponseDto<List<PostDto.Get>>> findAllByPublic() {
        List<Post> posts = postService.findAllByPublic();
        List<PostDto.Get> results = postMapper.postListToGetRequest(posts);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/group/{group-id}")
    public ResponseEntity<SingleResponseDto<List<PostDto.Get>>> findAllByGroupId(@PathVariable("group-id") Long groupId) {
        List<Post> posts = postService.findAllByGroupId(groupId);
        List<PostDto.Get> results = postMapper.postListToGetRequest(posts);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/group/member/{member-id}")
    public ResponseEntity<SingleResponseDto<List<PostDto.Get>>> findAllByMemberId(@PathVariable("member-id") Long memberId) {
        List<Post> posts = postService.findAllByMemberId(memberId);
        List<PostDto.Get> results = postMapper.postListToGetRequest(posts);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<SingleResponseDto<PostDto.Get>> findById(@PathVariable("post-id") Long postId) {
        Post post = postService.findById(postId);
        PostDto.Get result = postMapper.postToGetRequest(post);
        return ResponseEntity.ok(new SingleResponseDto<>(result));
    }

    @PostMapping
    public ResponseEntity<?> createNewPost(@RequestBody PostDto.Post postRequest) {
        Post post = postMapper.postRequestToPost(postRequest);
        Post saved = postService.createNewPost(post);

        URI location = createLocation(saved.getId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping
    public ResponseEntity<?> updatePost(@RequestBody PostDto.Patch patchRequest) {
        Post post = postService.updatePost(patchRequest);

        URI location = createLocation(post.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> deletePost(@PathVariable("post-id") Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }

    private static URI createLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
