package com.busanit.spring.d_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    @Bean
    public Client client() {
        Client client = new Client();
        client.setHost("호스트");
        return client;
    }
    @Bean(initMethod = "connect", destroyMethod = "close")
    public Client2 client2(){
        Client2 client2 = new Client2();
        client2.setHost("호스트2");
        return client2;
    }
}
