package com.enjoytrip.post.repository;

import com.enjoytrip.post.entity.Post;
import com.enjoytrip.post.entity.PostScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByScope(PostScope scope);
    List<Post> findByGroup_Id(@Param("group_id") Long groupId);
    List<Post> findByWriter_Id(@Param("writer_id") Long writer_id);
}
