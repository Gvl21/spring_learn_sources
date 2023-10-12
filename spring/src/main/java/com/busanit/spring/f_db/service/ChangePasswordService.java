package com.busanit.spring.f_db.service;

import com.busanit.spring.f_db.domain.Member;
import com.busanit.spring.f_db.domain.MemberDao;
import com.busanit.spring.f_db.exception.MemberNotFoundException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// 관심사 : 비밀번호를 바꾸는 것
public class ChangePasswordService {

    private MemberDao memberDao;

    // 비밀번호 변경
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void changePassword(String email, String oldPassword, String newPassword) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPassword, newPassword);
        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
