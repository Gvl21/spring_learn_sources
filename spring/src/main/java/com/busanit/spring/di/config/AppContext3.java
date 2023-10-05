package com.busanit.spring.di.config;

import com.busanit.spring.di.domain.MemberDao;
import com.busanit.spring.di.service.MemberPrinter;
import org.springframework.context.annotation.Bean;
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
