package com.enjoytrip.auth.OAuth2Handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

    //OAuth2User 객체로 호출
    @GetMapping("/hello-oauth2/1")
    public String home(@AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println(
                "User's email in Google: " + oAuth2User.getAttributes().get("email")); //요소 추출

        return "hello-oauth2";
    }

    //Authentication 객체로 호출
    @GetMapping("/hello-oauth2/2")
    public String home2(Authentication authentication) {
        var oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println(oAuth2User);
        System.out.println("User's email in Google: " + oAuth2User.getAttributes().get("email"));

        return "hello-oauth2";
    }

    //Access Token 정보 확인 => OAuth2AuthorizedClientService 를 DI 받고 사용
    private final OAuth2AuthorizedClientService authorizedClientService;

    public OAuth2Controller(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/hello-oauth2")
    public String home(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("Access Token Value: " + accessToken.getTokenValue());
        System.out.println("Access Token Type: " + accessToken.getTokenType().getValue());
        System.out.println("Access Token Scopes: " + accessToken.getScopes());
        System.out.println("Access Token Issued At: " + accessToken.getIssuedAt());
        System.out.println("Access Token Expires At: " + accessToken.getExpiresAt());
        return "hello-oauth2";
    }
}