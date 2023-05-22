package com.enjoytrip.course.service;

import com.enjoytrip.course.dto.CourseDto;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseScope;
import com.enjoytrip.course.mapper.CourseMapper;
import com.enjoytrip.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<Course> findAllByPublic() {
        return courseRepository.findAllByScope(CourseScope.PUBLIC);
    }

    public List<Course> findAllByGroupId(Long groupId) {
        return courseRepository.findByGroup_Id(groupId);
    }

    public List<Course> findAllByMemberId(Long memberId) {
        return courseRepository.findByWriter_Id(memberId);
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public Course findById(Long courseId) {
        return courseRepository.findById(courseId).get();
    }

    public void updateCourse(Long courseId, CourseDto.Patch patchRequest) {
        Course course = courseRepository.findById(courseId).get();
        courseMapper.patchRequestToCourse(patchRequest, course);
    }

    public void deleteById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

}
