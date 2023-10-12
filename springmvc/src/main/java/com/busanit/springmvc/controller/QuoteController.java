package com.busanit.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuoteController {
    // 애노테이션 /ramdomquote -> 접속시, 랜덤한 명언
    @RequestMapping(value = "/randomquote", method = RequestMethod.GET)
    public String randomQuotes(Model model){

        // 랜덤 명언
        String[] quotes= {
                "포기하지 말고 꿈을 향해 나아가라.",
                "성공은 준비와 기회가 만나는 곳에서 시작된다.",
                "먼저 시작하면, 절반은 이미 성공한 것이다.",
                "오늘의 노력은 내일의 성과를 만든다.",
                "어제보다 나은 내일을 만들어라.",
                "실수는 배우기 위한 기회이다.",
                "계획 없는 목표는 단순한 희망에 불과하다.",
                "포용과 이해가 세상을 더 아름답게 만든다.",
                "노력은 늘 보상을 가져온다.",
                "긍정적인 마음으로 문제를 바라보면 해결책을 찾을 수 있다."
        };
        // 명언 배열 길이로 랜덤 수수자 추출
        // 모델에 랜덤한 명언을 추가
        // 뷰(머스태치 템플릿)으로 연결
        int randomNum = (int)(Math.random()*quotes.length);
        model.addAttribute("quotes", quotes[randomNum]);
     return "quote";
    }
}
