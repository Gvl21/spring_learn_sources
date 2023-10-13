package com.busanit.springmvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Entity
public class Member {
    @GeneratedValue
    @Id
    private Long id;
    @Column
    private String email;
    @Column
    private String pass;

    public Member(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public Member() {
    }
}
