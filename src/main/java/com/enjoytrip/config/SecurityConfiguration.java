package com.enjoytrip.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.enjoytrip.auth.CustomAuthorityUtils;
import com.enjoytrip.auth.JwtTokenizer;
import com.enjoytrip.auth.OAuth2MemberSuccessHandler;
import com.enjoytrip.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Value("${spring.security.oauth2.client.registration.google.clientId}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.clientSecret}")
    private String clientSecret;


    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;


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
//                .exceptionHandling() // 추가
//                .authenticationEntryPoint(new MemberAuthenticationEntryPoint()) // 추가
//                .accessDeniedHandler(new MemberAccessDeniedHandler()) // 추가
//                .and()
                .apply(new CustomFilterConfigurer()) // 추가
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.PATCH, "/*/coffees/**").hasRole("ADMIN") // SpringBoot 3 : antMatchers => requestMatchers
                        .requestMatchers(HttpMethod.GET, "/*/coffees/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/*/coffees").permitAll()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService))
                );


        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = clientRegistration();
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    private ClientRegistration clientRegistration() {
        return CommonOAuth2Provider
                .GOOGLE
                .getBuilder("google")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    public class CustomFilterConfigurer extends
            AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // (2)
        }
    }
}
