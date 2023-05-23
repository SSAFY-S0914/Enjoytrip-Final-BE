package com.enjoytrip.post.dto;

import com.enjoytrip.group.dto.GroupDto;
import com.enjoytrip.post.entity.PostScope;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostDto {

    @AllArgsConstructor
    @Getter
    public static class Get {

        private MemberDto.Get memberDto;
        private GroupDto.Get groupDto;
        private String title;
        private String content;
        private String createdAt;
        private String modifiedAt;
    }

    @AllArgsConstructor
    @Getter
    public static class Post {

        private Long writerId;
        private Long groupId;
        private String title;
        private String content;
        private PostScope scope;
    }

    @AllArgsConstructor
    @Getter
    public static class Patch {

        private Long postId;
        private Long writerId;
        private Long groupId;
        private String title;
        private String content;
        private PostScope scope;
    }
}
