package com.enjoytrip.member.entity;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.follow.entity.Follow;
import com.enjoytrip.like.entity.Like;
import com.enjoytrip.product.entity.Product;
import com.enjoytrip.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private LocalDateTime birth;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE; //회원 가입 시, 기본값으로 "활성 계정"의 값을 지정.

    @OneToMany(mappedBy = "member")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Follow> follows = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Course> groups = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();

    public enum MemberStatus {
        MEMBER_ACTIVE("활성 계정"),
        MEMBER_SLEEP("휴면 계정"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
