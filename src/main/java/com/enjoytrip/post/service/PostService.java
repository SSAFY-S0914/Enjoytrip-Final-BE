package com.enjoytrip.post.service;

import com.enjoytrip.post.dto.PostDto;
import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.entity.PostScope;
import com.enjoytrip.post.mapper.PostMapper;
import com.enjoytrip.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<Post> findAllByPublic() {
        return postRepository.findAllByScope(PostScope.PUBLIC);
    }

    public List<Post> findAllByGroupId(Long groupId) {
        return postRepository.findByGroup_Id(groupId);
    }

    public List<Post> findAllByMemberId(Long memberId) {
        return postRepository.findByWriter_Id(memberId);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).get();
    }

    public Post createNewPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(PostDto.Patch patchRequest) {
        Post post = findById(patchRequest.getPostId());
        postMapper.patchRequestToPost(patchRequest, post);
        return postRepository.save(post);
    }

    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }

}
