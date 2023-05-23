package com.enjoytrip.course.controller;

import com.enjoytrip.course.dto.CourseDto;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.mapper.CourseMapper;
import com.enjoytrip.course.service.CourseService;
import com.enjoytrip.utils.dtoUtils.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @GetMapping("public")
    public ResponseEntity<SingleResponseDto<List<CourseDto.Get>>> findAllByPublic() {
        List<Course> courses = courseService.findAllByPublic();
        List<CourseDto.Get> results = courseMapper.courseToGetRequest(courses);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/groups/{group-id}")
    public ResponseEntity<SingleResponseDto<List<CourseDto.Get>>> findAllByGroupId(@PathVariable("group-id") Long groupId) {
        List<Course> courses = courseService.findAllByGroupId(groupId);
        List<CourseDto.Get> results = courseMapper.courseToGetRequest(courses);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @GetMapping("/members/{member-id}")
    public ResponseEntity<SingleResponseDto<List<CourseDto.Get>>> findAllByMemberId(@PathVariable("member-id") Long memberId) {
        List<Course> courses = courseService.findAllByMemberId(memberId);
        List<CourseDto.Get> results = courseMapper.courseToGetRequest(courses);
        return ResponseEntity.ok(new SingleResponseDto<>(results));
    }

    @PostMapping
    public ResponseEntity<?> createNewCourse(@RequestBody CourseDto.Post postRequest) {
        Course saved = courseService.createNewCourse(postRequest);

        URI location = createLocation(saved.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<SingleResponseDto<CourseDto.Get>> findById(@PathVariable("course-id") Long courseId) {
        Course course = courseService.findById(courseId);
        CourseDto.Get result = courseMapper.courseToGetRequest(course);
        return ResponseEntity.ok(new SingleResponseDto<>(result));
    }

    @PatchMapping("/{course-id}")
    public ResponseEntity<?> updateCourse(@PathVariable("course-id") Long courseId,
                                          @RequestBody CourseDto.Patch patchRequest) {
        Course course = courseService.updateCourse(patchRequest, courseId);

        URI uri = createLocation(course.getId());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<?> deleteById(@PathVariable("course-id") Long courseId) {
        courseService.deleteById(courseId);
        return ResponseEntity.noContent().build();
    }

    private static URI createLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
