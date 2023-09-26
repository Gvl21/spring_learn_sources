package com.busanit.spring.hello;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 자바 설정에서 정보를 읽어와서 빈 객체를 생성하고 관리하는 Context 객체를 불러온다.
        // 스프링 컨테이너
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
//        Greeter greeter = new Greeter();
        // new 키워드를 통해서 객체를 생성하는 대신
        // AnnotationConfigApplicationContext <= 애노테이션 설정정보를 담고 있는 스프링 컨테이너
        // 가 대신해서 Bean 객체를 가져오게 한다.
        // 첫번째 파라미터 : Bean 객체의 이름
        // 두번째 파라미터 : Bean 객체의 타입.class
        Greeter greeter = ctx.getBean("greeter", Greeter.class);
//        greeter.setFormat("%s 안녕하세요");
        String helloMsg = greeter.greet("스프링");
        System.out.println(helloMsg);

        // 모든 Bean 객체는 싱글톤이다.
        Greeter greeter2 = ctx.getBean("greeter",Greeter.class);
        Greeter greeter3 = ctx.getBean("greeter",Greeter.class);

        // bean 객체는 하나의 인스턴스만 갖는다.
        System.out.println(greeter == greeter2);
        System.out.println(greeter2 == greeter3);

        // 스프링 컨테이너를 닫아준다.
        ctx.close();
    }
}
