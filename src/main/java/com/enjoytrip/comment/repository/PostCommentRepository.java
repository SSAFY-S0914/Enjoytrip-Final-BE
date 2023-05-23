//package com.enjoytrip.comment.repository;
//
//import com.enjoytrip.comment.entity.PostComment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
//
//    List<PostComment> findByPost_Id(Long postId);
//
//    List<PostComment> findByMember_Id(Long writerId);
//
//}
