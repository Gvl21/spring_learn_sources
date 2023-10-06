package com.busanit.spring.a_di.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Import(AppContext2.class)
@Import({AppContext1.class, AppContext2.class})
@Configuration
public class AppContext3 {

//
//    @Bean
//    MemberDao memberDao() {
//        return new MemberDao();
//    }
//
//    @Bean
//    public MemberPrinter memberPrinter() {
//        return new MemberPrinter();
//
//    }
}
