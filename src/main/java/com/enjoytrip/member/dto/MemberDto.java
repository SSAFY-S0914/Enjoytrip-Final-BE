package com.enjoytrip.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberDto {

    @AllArgsConstructor
    @Getter
    public static class Post {

        @NotEmpty(message = "이름을 반드시 입력해야 합니다.")
        private String name;

        @NotEmpty //TODO, @Pattern 추가하기
        private String password;

        @NotEmpty(message = "이메일을 반드시 입력해야 합니다.")
        @Email
        private String email;

//        @NotEmpty
//        private String nickname;

//        @NotNull(message = "휴대전화 번호를 반드시 입력해야 합니다.")
//        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
//        private String phoneNum;

//        @NotEmpty
//        private String birth;

//        @NotEmpty
//        private String profileImage;
    }

    @AllArgsConstructor
    @Getter
    public static class Patch {

        private String name;

        private String password;

        private String email;

        private String nickname;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
        private String phoneNum;

        private String birth;

        private String profileImage;

    }

    @AllArgsConstructor
    @Getter
    public static class Response {

        private Long id;
        private String name;
        private String password;
        private String email;
        private String nickname;
        private String phoneNum;
        private String birth;
        private String profileImage;

    }
}
