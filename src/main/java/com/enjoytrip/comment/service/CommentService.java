package com.enjoytrip.comment.service;

import com.enjoytrip.comment.dto.CommentDto;
import com.enjoytrip.comment.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<? extends Comment> findAllByTargetId(Long targetId);

    Comment findById(Long commentId);

    void createNewComment(Comment comment);

    void updateComment(CommentDto.Patch patchRequest, Comment comment);

    void deleteById(Long commentId);

    List<? extends Comment> findAllByMemberId(Long memberId);
}

