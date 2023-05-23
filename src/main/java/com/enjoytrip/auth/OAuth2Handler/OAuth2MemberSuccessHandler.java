package com.enjoytrip.auth.OAuth2Handler;

import com.enjoytrip.auth.jwt.JwtTokenizer;
import com.enjoytrip.auth.utils.CustomAuthorityUtils;
import com.enjoytrip.member.entity.Member;
import com.enjoytrip.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
public class OAuth2MemberSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    // SimpleUrlAuthenticationSuccessHandler : getRedirectStrategy().sendRedirect() 같은 API 사용을 위해

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
//    private final MemberService memberService;

    //onAuthenticationSuccess : // 인증 성공 후, 로그를 기록하거나 사용자 정보를 response로 전송하는 등의 추가 작업을 할 수 있다.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        var oAuth2User = (OAuth2User)authentication.getPrincipal(); // OAuth2 인증을 통해 사용자 정보 추출
        String email = String.valueOf(oAuth2User.getAttributes().get("email")); // 사용자 정보 중 email 추출(username)
        List<String> authorities = authorityUtils.createRoles(email); // 어플리케이션에서 사용할 인증서에 역할을 생성

//        saveMember(email); // 백 서버에서 활용하기 위해 최소한의 정보를 DB 에 저장(필요 없을 제외해도 되는지 확인)
        redirect(request, response, email, authorities); //
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String username
            , List<String> authorities) throws IOException {
        log.info("Token 생성 시작");
        String accessToken = delegateAccessToken(username, authorities); // username : email
        String refreshToken = delegateRefreshToken(username);
        String uri = createURI(accessToken, refreshToken).toString();
        getRedirectStrategy().sendRedirect(request, response, uri); // 생성한 토큰을 URI에 담고 다시 front 애플리케이션으로 redirect 실행
    }

    //AccessToken 생성
    private String delegateAccessToken(String username, List<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", authorities);
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
        return accessToken;
    }

    //RefreshToken 생성
    private String delegateRefreshToken(String username) {
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);
        return refreshToken;
    }

    //front로 redirect 하는 부분
    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        // user_id, username 을 붙여야할까?
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);
        return UriComponentsBuilder // 별도 설정이 없을 경우 uri 기본 port 값은 80
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(3000)
                .path("/")
//                .path("/receive-token.html")
                .path("/tokeninfo")
                .queryParams(queryParams)
                .build()
                .toUri();
    }

//    private void saveMember(String email) {
//        Member member = new Member(email);
//        memberService.createMember(member);
//    }
}
