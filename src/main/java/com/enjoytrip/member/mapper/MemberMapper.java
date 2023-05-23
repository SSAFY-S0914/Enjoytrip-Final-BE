package com.enjoytrip.member.mapper;

import com.enjoytrip.member.dto.MemberDto;
import com.enjoytrip.member.entity.Member;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostToMember(MemberDto.Post postRequest);

    Member memberPatchToMember(MemberDto.Patch patchRequest);

    MemberDto.Get memberToMemberResponse(Member member);

    List<MemberDto.Get> membersToMemberList(List<Member> members); // 여러 유저 조회

}
