package com.enjoytrip.comment.service;

import com.enjoytrip.comment.dto.CommentDto;
import com.enjoytrip.comment.dto.PostCommentDto;
import com.enjoytrip.comment.entity.Comment;
import com.enjoytrip.comment.entity.PostComment;
import com.enjoytrip.comment.mapper.PostCommentMapper;
import com.enjoytrip.comment.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentService implements CommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostCommentMapper postCommentMapper;

    @Override
    public List<PostComment> findAllByTargetId(Long targetId) {
        return postCommentRepository.findByPost_Id(targetId);
    }

    @Override
    public PostComment findById(Long commentId) {
        return postCommentRepository.findById(commentId).get();
    }

    @Override
    public void createNewComment(Comment comment) {
        postCommentRepository.save((PostComment) comment);
    }

    @Override
    public void updateComment(CommentDto.Patch patchRequest, Comment comment) {
        postCommentMapper.patchRequestToPostComment((PostCommentDto.Patch) patchRequest, (PostComment) comment);
        postCommentRepository.save((PostComment) comment);
    }

    @Override
    public void deleteById(Long commentId) {
        postCommentRepository.deleteById(commentId);
    }

    @Override
    public List<PostComment> findAllByMemberId(Long memberId) {
        return postCommentRepository.findByMember_Id(memberId);
    }
}
