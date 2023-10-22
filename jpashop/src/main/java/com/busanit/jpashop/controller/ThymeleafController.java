package com.busanit.jpashop.controller;

import com.busanit.jpashop.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class ThymeleafController {
    @GetMapping("/ex01")
    public String ex01(Model model){
        model.addAttribute("data", "타임리프다.");
        return "thymeleaf/ex01";
    }
    public static String ex2(Model model){
        ItemDto itemDto = ItemDto.builder().itemNm("상품명1").itemDetail("상품상세")
                .price(10000).regTime(LocalDateTime.now()).build();
        model.addAttribute("item", itemDto);
        return "thymeleaf/ex02";
    }

}
