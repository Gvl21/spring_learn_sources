package com.busanit.spring.a_di.config;

import com.busanit.spring.a_di.domain.MemberDao;
import com.busanit.spring.a_di.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext5 {
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegisterService() {
        // 생성자를 통해서 의존 Bean 객체(memberDao())를 주입
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        var changePasswordService = new ChangePasswordService();
        // 세터를 통해서 의존 Bean 객체(memberDao())를 주입
        changePasswordService.setMemberDao(memberDao());
        return changePasswordService;
    }

//    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    @Bean
    MemberListPrinter memberListPrinter() {
        // 생성자를 통한 의존성 주입
        return new MemberListPrinter(memberPrinter(), memberDao());
    }

    @Bean
    MemberInfoPrinter memberInfoPrinter() {
        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
        // 세터를 통한 의존성 주입
        memberInfoPrinter.setMemberPrinter(memberPrinter());
        memberInfoPrinter.setMemberDao(memberDao());
        return memberInfoPrinter;
    }

    // 기본 데이터 타입 값 설정하기
    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(6);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

    @Bean
    public VersionPrinter oldVersionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
