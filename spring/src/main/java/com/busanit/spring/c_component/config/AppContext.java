package com.busanit.spring.c_component.config;

import com.busanit.spring.c_component.service.MemberPrinter;
import com.busanit.spring.c_component.service.MemberSummaryPrinter;
import com.busanit.spring.c_component.service.VersionPrinter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// 애노테이션 기준으로 컴포넌트 스캔 제외
//@ComponentScan(basePackages = {"com.busanit.spring.c_component"},
//        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ManualBean.class}))

// 애노테이션 기준으로 컴포넌트 스캔 제외
//@ComponentScan(basePackages = {"com.busanit.spring.c_component"},
//        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MemberDao.class}))
@ComponentScan(basePackages = {"com.busanit.spring.c_component"})
@Configuration
public class AppContext {

    @Bean
    @Primary  // 동일한 타입에서 가장 우선순위를 가짐
    @Qualifier("printer")
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }


    // 기본 데이터 타입 값 설정하기
    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();

        versionPrinter.setMajorVersion(6);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
