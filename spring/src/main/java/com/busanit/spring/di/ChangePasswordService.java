package com.busanit.spring.di;

// 관심사 : 비밀번호를 바꾸는 것
public class ChangePasswordService {

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

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
