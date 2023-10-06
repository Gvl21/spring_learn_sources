package com.busanit.spring.f_db.service;

import com.busanit.spring.f_db.domain.Member;
import com.busanit.spring.f_db.domain.MemberDao;

import java.util.Collection;

public class MemberListPrinter {
    /* 멤버 목록을 프린트하는 클래스 */
    private MemberPrinter printer;
    private MemberDao memberDao;

    // DI(의존성 주입) 생성자 주입 : 프린터 + 데이터접근
    public MemberListPrinter(MemberPrinter printer, MemberDao memberDao) {
        this.printer = printer;
        this.memberDao = memberDao;
    }

    // 배열(반복가능 컬렉션)을 순회하며, 프린트
    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        // 람다 표현식
        // members.forEach(member -> printer.print(member));
        // 메서드 참조
        members.forEach(printer::print);
    }
}
