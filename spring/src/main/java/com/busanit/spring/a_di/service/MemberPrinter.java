package com.busanit.spring.a_di.service;

import com.busanit.spring.a_di.domain.Member;

// SRP 적용 : 단일 책임 원칙
public class MemberPrinter {
    public void print(Member member) {
        // %t 스트링 포맷에서 날짜형식
        // %tF yyyy-MM-dd
        // %tT hh:mm:ss
        // %ty 년도 %tm 월 .....

        System.out.printf("회원정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getRegisterDateTime());
    }
}
