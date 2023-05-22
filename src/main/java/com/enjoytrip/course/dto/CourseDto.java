package com.enjoytrip.course.dto;

import com.enjoytrip.course.entity.CourseScope;
import com.enjoytrip.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class CourseDto {

    @AllArgsConstructor
    @Getter
    public static class Get {
        private String title;
        private String content;
        private MemberDto.Get memberDto;
        private List<String> productId;
    }

    @AllArgsConstructor
    @Getter
    public static class Post {
        private String title;
        private String content;
        private CourseScope scope;
        private Long memberId;
        private List<String> productIdList;
    }

    @AllArgsConstructor
    @Getter
    public static class Patch {
        private Long courseId;
        private String title;
        private String content;
        private CourseScope scope;
        private Long memberId;
        private List<String> productIdList;
    }

}
