package com.busanit.spring.di.config;

import com.busanit.spring.di.domain.MemberDao;
import com.busanit.spring.di.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    // @Bean 객체와 싱글톤
    // memberDao() 는 다른 메서드에서 여러번 호출괸다.
    // 호출될 때 마다 new 키워드로 생성이 되어 리턴된다.
    // 다른 객체? => 각각의 리턴된 객체들은 모두 싱글톤 객체
    // 스프링 컨테이너는 @Bean 어노테이션이 있는 메서드에 대헤
    // 단 하나의 객체만 생성한다.
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
    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }
    @Bean
    MemberListPrinter memberListPrinter(){
        return new MemberListPrinter(memberPrinter(), memberDao());
    }
    @Bean
    MemberInfoPrinter memberInfoPrinter(){
        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
        // 세터를 통한 의존성 주입
        memberInfoPrinter.setMemberPrinter(memberPrinter());
        memberInfoPrinter.setMemberDao(memberDao());
        return memberInfoPrinter;
    }
    // 기본 데이터 타입 값 설정하기
    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(6);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
