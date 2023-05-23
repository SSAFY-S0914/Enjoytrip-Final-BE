package com.enjoytrip.course.mapper;

import com.enjoytrip.course.dto.CourseDto;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class CourseMapper {

    private final MemberService memberService;

    public abstract CourseDto.Get courseToGetRequest(Course course);

    public abstract List<CourseDto.Get> courseToGetRequest(List<Course> course);

    @Mapping(target = "writer", source = "postRequest.memberId", qualifiedByName = "mapMember")
    public abstract Course postRequestToCourse(CourseDto.Post postRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writer", ignore = true)
    public abstract void patchRequestToCourse(CourseDto.Patch patchRequest, @MappingTarget Course course);

    @Named("mapMember")
    protected Member mapMember(Long memberId) {
        return memberService.findOneMember(memberId);
    }

}
