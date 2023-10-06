package com.busanit.spring.e_aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        Calculator cal = context.getBean(Calculator.class);
        cal.factorial(30);
        System.out.println(cal.getClass().getName());



        context.close();
    }
}
