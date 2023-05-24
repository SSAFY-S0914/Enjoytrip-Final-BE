package com.enjoytrip.member.mapper;

import com.enjoytrip.member.dto.MemberDto.Get;
import com.enjoytrip.member.dto.MemberDto.Patch;
import com.enjoytrip.member.dto.MemberDto.Post;
import com.enjoytrip.member.entity.Member;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-24T09:36:04+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(Post postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        Member member = new Member();

        member.setName( postRequest.getName() );
        member.setPassword( postRequest.getPassword() );
        member.setEmail( postRequest.getEmail() );

        return member;
    }

    @Override
    public Member memberPatchToMember(Patch patchRequest) {
        if ( patchRequest == null ) {
            return null;
        }

        Member member = new Member();

        member.setName( patchRequest.getName() );
        member.setPassword( patchRequest.getPassword() );
        member.setEmail( patchRequest.getEmail() );
        member.setNickname( patchRequest.getNickname() );
        member.setPhoneNum( patchRequest.getPhoneNum() );
        if ( patchRequest.getBirth() != null ) {
            member.setBirth( LocalDateTime.parse( patchRequest.getBirth() ) );
        }
        member.setProfileImage( patchRequest.getProfileImage() );

        return member;
    }

    @Override
    public Get memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String password = null;
        String email = null;
        String nickname = null;
        String phoneNum = null;
        String birth = null;
        String profileImage = null;

        id = member.getId();
        name = member.getName();
        password = member.getPassword();
        email = member.getEmail();
        nickname = member.getNickname();
        phoneNum = member.getPhoneNum();
        if ( member.getBirth() != null ) {
            birth = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( member.getBirth() );
        }
        profileImage = member.getProfileImage();

        Get get = new Get( id, name, password, email, nickname, phoneNum, birth, profileImage );

        return get;
    }

    @Override
    public List<Get> membersToMemberList(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<Get> list = new ArrayList<Get>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }
}
