package com.enjoytrip.comment.service;

import com.enjoytrip.comment.dto.CommentDto;
import com.enjoytrip.comment.dto.ProductCommentDto;
import com.enjoytrip.comment.entity.Comment;
import com.enjoytrip.comment.entity.ProductComment;
import com.enjoytrip.comment.mapper.ProductCommentMapper;
import com.enjoytrip.comment.repository.ProductCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier(value = "ProductCommentService")
public class ProductCommentService implements CommentService {

    private final ProductCommentRepository productCommentRepository;
    private final ProductCommentMapper productCommentMapper;

    @Override
    public List<ProductComment> findAllByTargetId(Long targetId) {
        return productCommentRepository.findByProduct_Id(targetId);
    }

    @Override
    public Comment findById(Long commentId) {
        return productCommentRepository.findById(commentId).get();
    }

    @Override
    public Comment createNewComment(Comment comment) {
        return productCommentRepository.save((ProductComment) comment);
    }

    @Override
    public Comment updateComment(CommentDto.Patch patchRequest, Comment comment) {
        productCommentMapper.patchRequestToProductComment((ProductCommentDto.Patch) patchRequest, (ProductComment) comment);
        return productCommentRepository.save((ProductComment) comment);
    }

    @Override
    public void deleteById(Long commentId) {
        productCommentRepository.deleteById(commentId);
    }

    @Override
    public List<ProductComment> findAllByMemberId(Long memberId) {
        return productCommentRepository.findByMember_Id(memberId);
    }

}
