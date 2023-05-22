package com.enjoytrip.course.repository;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByScope(CourseScope scope);

    List<Course> findByGroup_Id(@Param("group_id") Long groupId);

    List<Course> findByWriter_Id(@Param("writer_id") Long writerId);
}
