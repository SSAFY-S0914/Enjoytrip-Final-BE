package com.enjoytrip.auth.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.enjoytrip.auth.CustomAuthorityUtils;
import com.enjoytrip.auth.JwtTokenizer;
import com.enjoytrip.auth.OAuth2MemberSuccessHandler;
import com.enjoytrip.auth.filter.JwtAuthenticationFilter;
import com.enjoytrip.auth.filter.JwtVerificationFilter;
import com.enjoytrip.member.service.MemberService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
//    @Value("${spring.security.oauth2.client.registration.google.clientId}")
//    private String clientId;
//    @Value("${spring.security.oauth2.client.registration.google.clientSecret}")
//    private String clientSecret;


    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
//    private final MemberService memberService;


    //Oauth 2_google 적용
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 세션 정책 : 세션을 생성하지 않도록 설정 => JWT 사용 환경에선 세션을 사용하지 않음
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer()) // 커스텀 필터 추가
                .and()
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.POST, "/*/members").permitAll() // 회원 가입 : ALL
                                .requestMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER") // 회원 수정 : User
                                .requestMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN") // 회원 정보 목록 : Admin
                                .requestMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN") // 회원 조회 : User, Admin
                                .requestMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER") // 회원 탈퇴 : User
//                        .requestMatchers(HttpMethod.PATCH, "/*/coffees/**").hasRole("ADMIN") // SpringBoot 3 : antMatchers => requestMatchers
//                        .requestMatchers(HttpMethod.GET, "/*/coffees/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/*/coffees").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/hello-oauth2").permitAll()
                        .anyRequest().permitAll()
                );
//                .oauth2Login(oauth2 -> oauth2
//                        .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService))
//                );


        return http.build();
    }

    //PasswordEncoder Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    //CORS 처리
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    //CustomFilterConfigurer => custom filter 를 filter chain 에 추가 하기 위한 용도
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class); //AuthenticationManager 객체를 얻음
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer); // JwtAuthenticationFilter 에 AuthenticationManager 와 JwtTokenizer 를 DI
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login"); // default URL : "/login"
            builder.addFilter(jwtAuthenticationFilter);

//            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
//            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // (2)
        }
    }

    // 'org.springframework.boot:spring-boot-starter-oauth2-client가 자동 구성해주는 부분
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        ClientRegistration clientRegistration = clientRegistration();
//        return new InMemoryClientRegistrationRepository(clientRegistration);
//    }
//
//    private ClientRegistration clientRegistration() {
//        return CommonOAuth2Provider
//                .GOOGLE
//                .getBuilder("google")
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .build();
//    }
}
