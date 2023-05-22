package com.enjoytrip.comment.dto;

import com.enjoytrip.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class ProductCommentDto {

    @AllArgsConstructor
    @Getter
    public static class Get {

        private Long commentId;
        private String content;
        private MemberDto.Get writer;
        private Integer star;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAd;
    }

    @AllArgsConstructor
    @Getter
    public static class Post {

        private Long writerId;
        private String content;
        private Integer star;
    }

    @AllArgsConstructor
    @Getter
    public static class Patch {

        private Long writerId;
        private String content;
        private Integer star;
    }

}
