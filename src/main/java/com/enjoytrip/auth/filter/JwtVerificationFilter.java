package com.enjoytrip.auth.filter;

import com.enjoytrip.auth.CustomAuthorityUtils;
import com.enjoytrip.auth.JwtTokenizer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
//OncePerRequestFilter : request 당 한 번 실행되는 필터
public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer; //JWT 검증 및 Claims 추출
    private final CustomAuthorityUtils authorityUtils; //검증에 성공 후 Authentication 객체에 사용자 권한 채우기

    /*
    1.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> claims = verifyJws(request); // JWT 검증 (JWS; JWT Signed)
        setAuthenticationToContext(claims); // SecurityContext 에 Authentication 객체 저장

        filterChain.doFilter(request, response);
    }

    //shouldNotFilter : 특정 조건에 부합되면 다음 Filter 로 건너뛰게하는 Filter
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");

        return authorization == null || !authorization.startsWith("Bearer");
    }

    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", ""); // header 에 존재하는 Bearer 제거
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey()); // JwtTokenizer 객체 필드로 존재하는 secret 키 호출 및 인코딩 키 생성
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody(); // Claims 파싱 => 정상 파싱은 곧 검증 성공을 의미

        return claims;
    }


    private void setAuthenticationToContext(Map<String, Object> claims) {
        String username = (String) claims.get("username"); //파싱된 claims에서 username 추출
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List) claims.get("roles")); // List<GrantedAuthority> 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities); // username 과 List<GrantedAuthority> 를 기반으로 Authentication 객체 생성
        SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext 에 Authentication 객체 저장
    }
}
