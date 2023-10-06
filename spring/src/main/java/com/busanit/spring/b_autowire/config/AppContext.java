package com.busanit.spring.b_autowire.config;

import com.busanit.spring.b_autowire.domain.MemberDao;
import com.busanit.spring.b_autowire.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
        return new MemberRegisterService();
    }
    @Bean
    public ChangePasswordService changePasswordService(){
       // AutoWired를 통해서 자동주입이 되었기 떄문에, 세터를 통해 별도로 주입하지 않아도 해당 필드에 해당 타입의 빈 객체를 찾아 주입한다
        // 세터를 통해서 의존 Bean 객체(memberDao())를 주입6
//        changePasswordService.setMemberDao(memberDao());
        return new ChangePasswordService();
    }
    // autowired로 자동주입 시 해당 타입 빈이 2개 이상 있을경우 예외 발생
    // @Qualifier 으로 의존 주입 대상을 한정한다.
    // 해당 타입 Autowired에도 모두 애노테이션 추가
    @Bean
    @Primary    // 동일한 타입에서 가장 우선순위를 가짐
    @Qualifier("printer")
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }
    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2(){
        return new MemberSummaryPrinter();
    }

    @Bean
    public MemberPrinter memberPrinter1(){
        return new MemberPrinter();
    }
    @Bean
    MemberListPrinter memberListPrinter(){
        return new MemberListPrinter();
    }
    @Bean
    MemberInfoPrinter memberInfoPrinter(){
        /* AutoWired를 이용한 자동 의존성 주입
        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();

        // 세터를 통한 의존성 주입
//        memberInfoPrinter.setMemberPrinter(memberPrinter());
//        memberInfoPrinter.setMemberDao(memberDao());*/

        return new MemberInfoPrinter();
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
