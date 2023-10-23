package com.busanit.jpashop.controller;

import com.busanit.jpashop.dto.MemberDto;
import com.busanit.jpashop.entity.Member;
import com.busanit.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/members/new")
    public String memberForm(Model model){
        model.addAttribute("memberDto",new MemberDto());
        return "member/memberForm";
    }
    @PostMapping("/members/new")
    public String memberForm(MemberDto memberDto){
        // Post - Redirect - Get Pattern
        // 생성 메서드 dto => entity
        Member member = Member.createMember(memberDto, passwordEncoder);
        // entity -> db 에 저장
        memberService.saveMember(member);
        // 리다이렉트
        return "redirect:/";
    }
    @GetMapping("/")
    public String main(){
        return "main";
    }
}
