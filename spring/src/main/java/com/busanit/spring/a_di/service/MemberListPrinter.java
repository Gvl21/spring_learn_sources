package com.busanit.spring.a_di.service;

import com.busanit.spring.a_di.domain.Member;
import com.busanit.spring.a_di.domain.MemberDao;

import java.util.Collection;

public class MemberListPrinter {
    /*
    * 멤버 목록을 프린트하는 클래스
    * */
    private MemberPrinter printer;
    private MemberDao memberDao;

    // 생성자 주입 : 프린터 + 데이터 접근
    public MemberListPrinter(MemberPrinter printer, MemberDao memberDao) {
        this.printer = printer;
        this.memberDao = memberDao;
    }

    // 배열(반복가능 컬렉션)을 순회하며, 프린트
    public void printAll(){
        Collection<Member> members = memberDao.selectAll();
        // 람다 표현식
//        members.forEach(member -> {printer.print(member);});
        // 메서드 참조
        members.forEach(printer::print);
    }

}
