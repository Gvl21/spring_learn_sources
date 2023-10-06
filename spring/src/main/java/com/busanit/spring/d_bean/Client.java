package com.busanit.spring.d_bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 빈의 생명주기
// InitializingBean : 빈 객체가 초기화 될때
// DisposableBean : 빈 객체가 소멸할 때
public class Client implements InitializingBean, DisposableBean {
    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    // 초기화 작업
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Client.afterPropertiesSet 실행");
        System.out.println("빈이 생성되기 전 초기화 단계, 생성될 때 필요한 작업");
        System.out.println("=======================================");
    }
    public  void  send(){
        System.out.println("=======================================");
        System.out.println("send 호출" + host);
        System.out.println("=======================================");
    }
    // 소멸 전 작업
    @Override
    public void destroy() throws Exception {
        System.out.println("=======================================");
        System.out.println("Client.destroy 실행");
        System.out.println("빈이 종료되기 전 필요한 작업");
    }
}
