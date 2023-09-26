package com.busanit.spring.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
    @Bean
    MemberDao memberDao(){
        return new MemberDao();
    }
    @Bean
    public MemberRegisterService memberRegisterService(){
        // 생성자를 통해서 의존 객체를 주입
        return new MemberRegisterService(memberDao());
    }
    @Bean
    public ChangePasswordService changePasswordService(){
        var changePasswordService = new ChangePasswordService();
        // 세터를 통해서 의존 Bean 객체(memberDao())를 주입6
        changePasswordService.setMemberDao(memberDao());
        return changePasswordService;
    }
}
