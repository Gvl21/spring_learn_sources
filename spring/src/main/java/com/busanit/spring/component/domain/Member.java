package com.busanit.spring.component.domain;

import com.busanit.spring.component.exception.WrongPasswordException;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime registerDateTime;

    public Member(String email, String password, String name, LocalDateTime registerDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = registerDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void changePassword(String oldPassword, String newPassword) {
        // 만약 패스워드가 일치하지 않는다면, 오류 발생
        if (!password.equals(oldPassword)) {
            throw new WrongPasswordException();
        }
        // 아니면 비밀번호 변경
        this.password = newPassword;
    }
}
