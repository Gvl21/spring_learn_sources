package com.busanit.spring.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애노테이션
// 환경 설정 정보를 담고 있는 객체
@Configuration
public class AppContext {
    // 스프링이 생성하는 객체를 Bean이라고 한다. (Spring Bean)

    // 객체를 생성하고 초기화하는 설정 정보를 담고 있다.
    // 메서드 이름으로 Bean 객체를 구분하는 이름으로 등록한다.
    @Bean
    public Greeter greeter(){
        Greeter greeter = new Greeter();
        greeter.setFormat("%s, 안녕하세요");
        return greeter;
    }
}
