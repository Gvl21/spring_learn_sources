package com.busanit.spring.a_di.config;

import com.busanit.spring.a_di.domain.MemberDao;
import com.busanit.spring.a_di.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext2 {
    // 스프링의 자동 주입 기능을 위한 애노테이션
    // Autowired를 사용할 경우, 스프링 컨테이너는 타입을 기준으로 해당 타입의 Bean객체를 찾아서 할당

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter memberPrinter;

    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService(memberDao);
    }


    @Bean
    public ChangePasswordService changePasswordService(){
        var changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao);
        return changePasswordService;
    }

    @Bean
    MemberListPrinter memberListPrinter(){
        return new MemberListPrinter(memberPrinter, memberDao);
    }
    @Bean
    MemberInfoPrinter memberInfoPrinter(){
        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
        memberInfoPrinter.setMemberPrinter(memberPrinter);
        memberInfoPrinter.setMemberDao(memberDao);
        return memberInfoPrinter;
    }



    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(6);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
