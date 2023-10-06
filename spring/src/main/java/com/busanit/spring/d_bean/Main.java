package com.busanit.spring.d_bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 컨테이너 생성과 함께 + 빈 객체 생성 + 초기화 메서드
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

        Client client = context.getBean(Client.class);
        client.send();
        Client2 client2 = context.getBean(Client2.class);
        client2.send();

        // 컨테이너 종료와 함께 소멸 메소드 호출
        context.close();
    }
}
