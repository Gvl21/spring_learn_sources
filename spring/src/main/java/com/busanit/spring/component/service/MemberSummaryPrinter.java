package com.busanit.spring.component.service;

import com.busanit.spring.component.domain.Member;

// SRP 적용 : 단일 책임 원칙
public class MemberSummaryPrinter extends MemberPrinter {
    @Override
    public void print(Member member) {

        System.out.printf("회원정보 :이메일=%s, 이름=%s\n",

                member.getEmail(),
                member.getName());

    }
}
