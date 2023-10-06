package com.busanit.spring.f_db.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    // 스프링 컨테이너 종료시 DB연결 종료
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        // 커넥션 풀 초기 개수
        dataSource.setInitialSize(5);
        // 커넥션 풀 최대 개수
        dataSource.setMaxActive(100);
        return dataSource;

    }

    @Bean
    public DBQuery dbQuery() {
        return new DBQuery(dataSource());
    }
}
