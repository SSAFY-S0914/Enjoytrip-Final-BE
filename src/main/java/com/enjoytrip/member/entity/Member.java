package com.enjoytrip.member.entity;

import com.enjoytrip.follow.entity.Follow;
import com.enjoytrip.group.entity.GroupMember;
import com.enjoytrip.like.entity.Like;
import com.enjoytrip.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

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

//    @Column(nullable = false)
    private String email;

//    @Column(nullable = false)
    private String nickname;

//    @Column(nullable = false)
    private String phoneNum;

//    @Column(nullable = false)
    private LocalDateTime birth;

//    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE; //회원 가입 시, 기본값으로 "활성 계정"의 값을 지정.

    // 권한 필드 추가
    // @ElementCollection : 해당 객체가 Collection 객체임을 JPA 에게 전파
    //FetchType.LAZY : 필요할 때 가져오기, FetchType.EAGER: 바로 가져오기
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

//    @OneToMany(mappedBy = "member")
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Follow> follows = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<GroupMember> groupMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();

    //로그인 인증에 사용
    public Member(String email) {
        this.email = email;
    }

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
