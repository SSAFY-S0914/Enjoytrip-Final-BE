package com.enjoytrip.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class PostCommentDto {

    @AllArgsConstructor
    @Getter
    public static class Get {

        private Long commentId;
        private String content;
        private String writer;
        private Long writerId;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @AllArgsConstructor
    @Getter
    public static class Post {

        private String content;
        private Long writerId;
    }

    @AllArgsConstructor
    @Getter
    public static class Patch {

        private String content;
        private Long writerId;
    }

}
