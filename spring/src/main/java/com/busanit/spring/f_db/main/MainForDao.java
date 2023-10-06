package com.busanit.spring.f_db.main;

import com.busanit.spring.f_db.config.AppContext;
import com.busanit.spring.f_db.domain.Member;
import com.busanit.spring.f_db.domain.MemberDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForDao {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        MemberDao memberDao = context.getBean(MemberDao.class);
        Member member = memberDao.selectByEmail("test@test.com");
        System.out.println(member.getEmail() + " " + member.getName());

        context.close();
    }
}
