package com.busanit.spring.f_db.main;

import com.busanit.spring.f_db.config.AppContext;
import com.busanit.spring.f_db.domain.Member;
import com.busanit.spring.f_db.domain.MemberDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.Collection;

public class MainForDao {
    private static MemberDao memberDao;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

        memberDao = context.getBean(MemberDao.class);

        insertMember();
        selectByEmail();
        selectAll();
//        updateMember();
        context.close();
    }

    private static void insertMember() {
        System.out.println("=====  MainforDao insert");
        Member member = new Member("asdf@test.com", "1234", "Kim", LocalDateTime.now());
        memberDao.insert(member);
        System.out.println("멤버 데이터 추가");
    }

    private static void updateMember() {
        System.out.println("=====  MainforDao update");
        // SQL 문으로 조회한 DAO에서 가져온 멤버 객체 비밀번호 변경시 정상 업데이트 되는지 확인
        String email = "test2@test.com";
        Member member = memberDao.selectByEmail(email);
        member.changePassword("12345","1234");
        memberDao.update(member);

        Member changedMember = memberDao.selectByEmail(email);
        System.out.println(changedMember.getPassword());
    }

    // 전체 조회 테스트
    private static void selectAll() {
        System.out.println("=====  MainforDao selectAll");
        Collection<Member> members = memberDao.selectAll();
        for (Member member :
                members) {
            System.out.println(member.getEmail() + " " + member.getName());
        }
    }
    // 개별 조회 테스트
    private static void selectByEmail() {
        System.out.println("=====  MainforDao selectByEmail");
        Member member = memberDao.selectByEmail("test@test.com");
        System.out.println(member);
        System.out.println(member.getEmail() + " " + member.getName());
    }


}
