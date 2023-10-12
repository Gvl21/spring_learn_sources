package com.busanit.springmvc.controller;

import com.busanit.springmvc.dto.MemberForm;
import com.busanit.springmvc.entity.Member;
import com.busanit.springmvc.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member/new")
    public String newMemberForm(){
        return "members/new";
    }
    // CREATE
    @PostMapping("/member/create")
    public String join(MemberForm dto){
        log.info(dto.toString());

        Member newMember = dto.toEntity();
        Member saved = memberRepository.save(newMember);
        log.info(saved.toString());

        return "";
    }
    @GetMapping("/member/list")
    public String memberList(Model model){
        Iterable<Member> list = memberRepository.findAll();
        model.addAttribute("memberList",list);

        return "/members/list";
    }
}
