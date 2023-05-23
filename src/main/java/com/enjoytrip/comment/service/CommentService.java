//package com.enjoytrip.comment.service;
//
//import com.enjoytrip.comment.dto.PostCommentDto;
//import com.enjoytrip.comment.dto.ProductCommentDto;
//import com.enjoytrip.comment.entity.PostComment;
//import com.enjoytrip.comment.entity.ProductComment;
//import com.enjoytrip.comment.mapper.CommentMapper;
//import com.enjoytrip.comment.repository.PostCommentRepository;
//import com.enjoytrip.comment.repository.ProductCommentRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CommentService {
//
//    private final PostCommentRepository postCommentRepository;
//    private final ProductCommentRepository productCommentRepository;
//    private final CommentMapper commentMapper;
//
//    public List<PostComment> findAllByPostId(Long postId) {
//        return postCommentRepository.findByPost_Id(postId);
//    }
//
//    public PostComment findPostCommentById(Long commentId) {
//        return postCommentRepository.findById(commentId).get();
//    }
//
//    public void createPostComment(PostComment postComment) {
//        postCommentRepository.save(postComment);
//    }
//
//    public void updatePostComment(PostComment postComment, PostCommentDto.Patch patchRequest) {
//        commentMapper.patchRequestToPostComment(patchRequest, postComment);
//        postCommentRepository.save(postComment);
//    }
//
//    public void deletePostCommentById(Long commentId) {
//        postCommentRepository.deleteById(commentId);
//    }
//
//    public List<ProductComment> findAllByProductId(Long productId) {
//        return productCommentRepository.findByProductId(productId);
//    }
//
//    public ProductComment findProductCommentById(Long commentId) {
//        return productCommentRepository.findById(commentId).get();
//    }
//
//    public void createProductComment(ProductComment productComment) {
//        productCommentRepository.save(productComment);
//    }
//
//    public void updateProductComment(ProductComment productComment, ProductCommentDto.Patch patchRequest) {
//        commentMapper.patchRequestToProductComment(patchRequest, productComment);
//        productCommentRepository.save(productComment);
//    }
//
//    public void deleteProductCommentById(Long commentId) {
//        productCommentRepository.deleteById(commentId);
//    }
//
//    public List<ProductComment> findAllProductCommentByMemberId(Long memberId) {
//        return productCommentRepository.findByMember_Id(memberId);
//    }
//
//    public List<PostComment> findAllPostCommentByMemberId(Long memberId) {
//        return postCommentRepository.findByMember_Id(memberId);
//    }
//}
//
