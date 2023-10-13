package com.busanit.springmvc.controller;

import com.busanit.springmvc.dto.MemberForm;
import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.entity.Member;
import com.busanit.springmvc.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        Member newMember = dto.toEntity();

        Member saved = memberRepository.save(newMember);
        log.info(saved.toString());

        return "redirect:/member/list";
    }
    //  READ
    @GetMapping("member/{id}")
    public String show(@PathVariable Long id, Model model){

        log.info("id : " + id);
        // 1. id 조회하여 데이터 가져오기
        Member member = memberRepository.findById(id).orElse(null);
        // 2. 모델에 데이터를 등록하기
        model.addAttribute("member", member);
        // 3. 뷰 페이지 View Resolver
        return "members/show";
    }
    // READ ALL
    @GetMapping("/member/list")
    public String memberList(Model model){
        Iterable<Member> list = memberRepository.findAll();
        model.addAttribute("memberList",list);

        return "/members/list";
    }
    // UPDATE
    @GetMapping("/member/{id}/edit")
    public String memberEdit(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);

        model.addAttribute("member",member);
        return "/members/edit";
    }

    // UPDATE
    @PostMapping("/member/{id}/update")
    public String memberUpdate(@PathVariable Long id, MemberForm dto){
        log.info(dto.toString());
        Member member = dto.toEntity();
        Member target = memberRepository.findById(id).orElse(null);
        if (target != null){
            member.setId(id);
            memberRepository.save(member);
        }




        return "redirect:/member/" + id;
    }


}
