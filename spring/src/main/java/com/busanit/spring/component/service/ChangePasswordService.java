package com.busanit.spring.component.service;

import com.busanit.spring.autowire.exception.MemberNotFoundException;
import com.busanit.spring.component.domain.Member;
import com.busanit.spring.component.domain.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;

// 관심사 : 비밀번호를 바꾸는 것
public class ChangePasswordService {

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // 자동 주입 기능 : 의존 객체를 명시하지 않아도, 스프링이 필요한 의존 빈 객체를 찾아서 주입
    @Autowired
    private MemberDao memberDao;

    // 비밀번호 변경
    public void changePassword(String email, String oldPassword, String newPassword) {
        Member member = memberDao.selectByEmail(email);
        // 1. 해당 email이 존재하는가 => if not => Exception
        if (member == null) {
            throw new MemberNotFoundException();
        }
        // 2. else 비밀번호 변경해라.
        member.changePassword(oldPassword, newPassword);
        // 3. dao를 통해 데이터에 반영해라.
        memberDao.update(member);
    }
}
