package com.enjoytrip.post.dto;

import com.enjoytrip.post.entity.PostScope;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostDto {

    @AllArgsConstructor
    @Getter
    public static class Get {

        private String nickname;
        private String title;
        private String content;
        private String createdAt;
        private String modifiedAt;
    }

    @AllArgsConstructor
    @Getter
    public static class Post {

        private Long group_id;
        private Long writer_id;
        private String title;
        private String content;
        private PostScope scope;
    }

    @AllArgsConstructor
    @Getter
    public static class Patch {

        private Long id;
        private Long group_id;
        private Long writer_id;
        private String title;
        private String content;
        private PostScope scope;
    }
}
