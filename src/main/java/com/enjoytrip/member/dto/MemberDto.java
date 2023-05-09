package com.enjoytrip.member.dto;

import com.enjoytrip.member.entity.Member;
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
        @NotEmpty(message = "이메일을 반드시 입력해야 합니다.")
        @Email
        private String email;
        @NotEmpty //TODO, @Pattern 추가하기
        private String password;
        @NotNull(message = "휴대전화 번호를 반드시 입력해야 합니다.")
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
        private String phoneNum;
        @NotEmpty
        private String address;

    }

    @AllArgsConstructor
    @Getter
    public static class Patch {

        private String name;
        private String email;
        private String password;
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
        private String phoneNum;
        private String address;
        private Member.MemberStatus memberStatus;

    }

    @AllArgsConstructor
    @Getter
    public static class Response {

        private Long memberId;
        private String name;
        private String email;
        private String password;
        private String phoneNum;
        private String address;
        private Member.MemberStatus memberStatus;
        
    }
}
