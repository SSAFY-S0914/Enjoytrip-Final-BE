package com.enjoytrip.group.repository;

import com.enjoytrip.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Course, Long> {
}
