package com.busanit.spring.c_component.domain;

import com.busanit.spring.c_component.config.ManualBean;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ManualBean
@Component
public class MemberDao {

    private static long nextId = 0;
    // Key : 이메일, Value : 멤버 객체를 담은 데이터 자료구조
    private Map<String, Member> map = new HashMap<>();
    // [ {key : value, key2 : Value , ... email : Member} ]

    // READ
    public Member selectByEmail(String email) {
        return map.get(email);
    }

    // CREATE
    public void insert(Member member) {
        member.setId(++nextId);
        map.put(member.getEmail(), member);
    }

    // UPDATE
    public void update(Member member) {
        map.put(member.getEmail(), member);
    }

    // READ ALL
    public Collection<Member> selectAll(){
        return map.values();
    }
}
