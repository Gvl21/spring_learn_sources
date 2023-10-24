package com.busanit.jpashop.config;

import com.busanit.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity  // 웹 보안
@Configuration      // 설정 정보 컴포넌트 등록 선언
@RequiredArgsConstructor
public class SecurityConfig {
    // 의존성 주입
    private final MemberService memberService;
    // http 요청에 대한 보안 설정
    @Bean       // 스프링 컨테이너에 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {




        http.formLogin(form ->
                        form
                        .loginPage("/members/login")            // 기본 로그인 페이지 URL을 설정
                        .defaultSuccessUrl("/")             // 로그인에 성공했을 때 URL
                        .usernameParameter("email")         // 로그인에 사용할 매개변수 username -> email
                        .passwordParameter("password")         // 로그인에 사용할 매개변수 username -> email
                        .failureUrl("/members/login/error")     // 실패했을 때 보낼 URL

        );
        http.logout(
                logout-> logout
                        .logoutRequestMatcher(
                                // Ant 패턴 경로 문법 -> 해당 URL 접속시 로그아웃 됨
                                new AntPathRequestMatcher("/members/logout"))
                        // 로그아웃이 성공한 경우 메인 페이지로 리다이렉트
                        .logoutSuccessUrl("/")
        );


        // CSRF 토큰 검증 무효화
        // http.csrf().disable();
        return http.build();
    }
    // 해시 함수를 이용한 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
