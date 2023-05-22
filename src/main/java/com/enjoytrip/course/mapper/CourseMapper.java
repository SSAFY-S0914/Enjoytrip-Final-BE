package com.enjoytrip.course.mapper;

import com.enjoytrip.course.dto.CourseDto;
import com.enjoytrip.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto.Get courseToGetRequest(Course course);

    List<CourseDto.Get> courseToGetRequest(List<Course> course);

    Course postRequestToCourse(CourseDto.Post postRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writer", ignore = true)
    void patchRequestToCourse(CourseDto.Patch patchRequest, @MappingTarget Course course);

}
