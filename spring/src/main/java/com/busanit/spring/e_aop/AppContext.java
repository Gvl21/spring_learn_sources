package com.busanit.spring.e_aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppContext {
    @Bean
    ExeTimeAspect exeTimeAspect(){
        return new ExeTimeAspect();
    }
    @Bean
    Calculator calculator(){
        return new CalculatorRe();
    }
    @Bean
    Calculator calculatorImpl(){
        return new CalculatorImpl();
    }
}
