package com.enjoytrip.course.controller;

import com.enjoytrip.course.dto.CourseDto;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.mapper.CourseMapper;
import com.enjoytrip.course.service.CourseService;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final MemberService memberService;

    @GetMapping("public")
    public ResponseEntity findAllByPublic() {
        List<Course> courses = courseService.findAllByPublic();
        List<CourseDto.Get> results = courseMapper.courseToGetRequest(courses);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/groups/{group-id}")
    public ResponseEntity findAllByGroupId(@PathVariable("group-id") Long groupId) {
        List<Course> courses = courseService.findAllByGroupId(groupId);
        List<CourseDto.Get> results = courseMapper.courseToGetRequest(courses);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/members/{member-id}")
    public ResponseEntity findAllByMemberId(@PathVariable("member-id") Long memberId) {
        List<Course> courses = courseService.findAllByMemberId(memberId);
        List<CourseDto.Get> results = courseMapper.courseToGetRequest(courses);
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity createNewCourse(@RequestBody CourseDto.Post postRequest) {
        Course course = courseMapper.postRequestToCourse(postRequest);
        Member member = memberService.findOneMember(postRequest.getMemberId());
        course.setWriter(member);
        courseService.save(course);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{course-id}")
    public ResponseEntity findById(@PathVariable("course-id") Long courseId) {
        Course course = courseService.findById(courseId);
        CourseDto.Get result = courseMapper.courseToGetRequest(course);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{course-id}")
    public ResponseEntity updateCourse(@PathVariable("course-id") Long courseId, @RequestBody CourseDto.Patch patchRequest) {
        courseService.updateCourse(courseId, patchRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity deleteById(@PathVariable("course-id") Long courseId) {
        courseService.deleteById(courseId);
        return ResponseEntity.ok().build();
    }

}
