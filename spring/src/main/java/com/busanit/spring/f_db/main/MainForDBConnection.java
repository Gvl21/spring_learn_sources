package com.busanit.spring.f_db.main;

import com.busanit.spring.f_db.config.DBConfig;
import com.busanit.spring.f_db.config.DBQuery;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForDBConnection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
        DBQuery dbQuery = context.getBean(DBQuery.class);
        int count = dbQuery.count();
        System.out.println(count);
        context.close();
    }
}
