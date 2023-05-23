package com.enjoytrip.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class LikeDto {

    @AllArgsConstructor
    @Getter
    public static class Post {
        Long memberId;
        Long targetId;
    }

    @AllArgsConstructor
    @Getter
    public static class Delete {
        Long memberId;
        Long targetId;
        Long likeId;
    }
}
