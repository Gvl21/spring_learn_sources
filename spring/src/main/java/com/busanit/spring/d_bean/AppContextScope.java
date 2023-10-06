package com.busanit.spring.d_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppContextScope {

    @Bean
    @Scope("prototype")
    public Client client() {
        Client client = new Client();
        client.setHost("호스트");
        return client;
    }

    @Scope("singleton")
    @Bean(initMethod = "connect", destroyMethod = "close")
    Client2 client2() {
        Client2 client2 = new Client2();
        client2.setHost("호스트2");
        return client2;
    }
}
