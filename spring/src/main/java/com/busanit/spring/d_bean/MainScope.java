package com.busanit.spring.d_bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainScope {
    public static void main(String[] args) {
        // 컨테이너 생성과 함께 + 빈 객체 생성 + 초기화 메서드
        var context = new AnnotationConfigApplicationContext(AppContextScope.class);

        Client client1 = context.getBean(Client.class);
        Client client2 = context.getBean(Client.class);
        Client client3 = context.getBean(Client.class);

        Client2 client4 = context.getBean(Client2.class);
        Client2 client5 = context.getBean(Client2.class);

        // 프로토 타입 스콥은 객체마다 다른 범위
        System.out.println(client1==client2);
        System.out.println(client1==client3);

        // 싱글톤 범위 : 같은 객체
        System.out.println(client4 == client5);

        // 컨테이너 종료와 함께 소멸 메서드 호출
        context.close();
    }
}
