package com.busanit.spring.a_di.config;

import com.busanit.spring.a_di.domain.MemberDao;
import com.busanit.spring.a_di.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext1 {

    @Bean
    MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();

    }
}
