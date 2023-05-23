package com.enjoytrip.post.service;

import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.entity.PostScope;
import com.enjoytrip.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).get();
    }

    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> findAllByPublic() {
        return postRepository.findAllByScope(PostScope.PUBLIC);
    }

    public List<Post> findAllByGroupId(Long groupId) {
        return postRepository.findByGroup_Id(groupId);
    }

    public List<Post> findAllByMemberId(Long memberId) {
        return postRepository.findByWriter_Id(memberId);
    }
}
