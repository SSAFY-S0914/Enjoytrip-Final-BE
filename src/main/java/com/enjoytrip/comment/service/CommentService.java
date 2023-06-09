package com.enjoytrip.comment.service;

import com.enjoytrip.comment.dto.CommentDto;
import com.enjoytrip.comment.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<? extends Comment> findAllByTargetId(Long targetId);

    Comment findById(Long commentId);

    Comment createNewComment(Comment comment);

    Comment updateComment(CommentDto.Patch patchRequest, Long commentId);

    void deleteById(Long commentId);

    List<? extends Comment> findAllByMemberId(Long memberId);
}

