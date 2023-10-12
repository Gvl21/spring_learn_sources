package com.busanit.springmvc.dto;

import com.busanit.springmvc.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private String email;
    private String pass;



    public Member toEntity(){
        return new Member(email, pass);
    }
}
