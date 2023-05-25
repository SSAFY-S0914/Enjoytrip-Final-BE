package com.enjoytrip.member.service;

import com.enjoytrip.auth.dto.LoginDto;
import com.enjoytrip.auth.utils.CustomAuthorityUtils;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.repository.MemberRepository;
import com.enjoytrip.utils.exception.BusinessLogicException;
import com.enjoytrip.utils.exception.ExceptionCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository repository;
    private final ApplicationEventPublisher publisher;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    public Member createMember(Member member) {
        //TODO 해당 멤버가 DB에 저장되어있는지 확인하는 로직 추가 필요(email-unique를 바탕으로)
        //Password 암호화(단방향), 백엔드에서 암호화된 상태로 사용하기에 단방향으로 설정
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        // DB에 User Role 저장
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        Member savedMember = repository.save(member);

//        publisher.publishEvent(new MemberRegistrationApplicationEvent(this, savedMember));
        return savedMember;
    }

    public Member updateMember(Member member) {
        Member findMember = findOneMember(member.getId());

        // Optional.ofNullable() : 인자로 준 값이 null 이 아니면 인자값을 그대로 반환하고, null 이면 new Optional<>(==빈 값) 반환
        // Optional.ifPresent() : 값이 주어지면 작동, 없으면 무시
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(passwordEncoder.encode(password)));
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
        Optional.ofNullable(member.getNickname())
                .ifPresent(nickName -> findMember.setNickname(nickName));
        Optional.ofNullable(member.getPhoneNum())
                .ifPresent(phoneNum -> findMember.setPhoneNum(phoneNum));
        Optional.ofNullable(member.getBirth())
                .ifPresent(birth -> findMember.setBirth(birth));
        Optional.ofNullable(member.getProfileImage())
                .ifPresent(profileImage -> findMember.setProfileImage(profileImage));

        return repository.save(findMember);
    }

    public Member findOneMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public Page<Member> findAllMembers(int page, int size) {
        Page<Member> pageMembers = repository.findAll(
                PageRequest.of(page, size, Sort.by("id").descending()));
        //repository.findAll() : Pageable 을 매개변수로 함.
        //PageRequest.of() : page, size, sort 를 같이 묶기 위한 Pageable 구현체
        //Sort.by() : 인자로 제공하는 문자열은 DB 와 매핑된 필드명이어야함. 즉, DB 에서 찾을 수 있어야 사용가능.
        return pageMembers;
    }

    public void deleteOneMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);
        repository.delete(findMember);
    }

    //기타 서비스로직들에 사용하며, 요구하는 사용자의 식별자(memberId)를 통해 DB에서 정보를 가져오는 메서드
    private Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = repository.findById(memberId);
        //Optional.orElseThrow() : 값이 존재하면(null이 아니면) 값을 반환하고, 값이 존재하지 않으면(null이면) 예외를 발생시킴.
        //customException 만들기
        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    public Member findByEmail (String email){
        Optional<Member> optionalMember = repository.findByEmail(email);

        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    public void changPassword (LoginDto member){
        Optional<Member> optionalMember = repository.findByEmail(member.getEmail());
        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        log.info("#bef: " + findMember.getEmail());
        log.info("#bef: " + findMember.getPassword());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        findMember.setPassword(encryptedPassword);
        log.info("#bef: " + findMember.getEmail());
        log.info("#bef: " + findMember.getPassword());
    }
}
