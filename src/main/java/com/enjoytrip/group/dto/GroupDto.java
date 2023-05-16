package com.enjoytrip.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class GroupDto {

    @AllArgsConstructor
    @Getter
    public static class Get {

        private Long groupId;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @AllArgsConstructor
    @Getter
    public static class Post {

        private String name;
        private String description;
    }

    @AllArgsConstructor
    @Getter
    public static class Patch {

        private String name;
        private String description;
    }
}
