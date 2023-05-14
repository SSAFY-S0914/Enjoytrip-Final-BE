package com.enjoytrip.post.dto;

import com.enjoytrip.post.entity.PostScope;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostDto {

    @AllArgsConstructor
    @Getter
    public static class Post {

        private Long writer_id;
        private Long group_id;
        private String title;
        private String content;
        private PostScope scope;
    }
}
