package com.busanit.jpashop.controller;

import com.busanit.jpashop.dto.ItemDto;
import com.busanit.jpashop.entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class ThymeleafController {
    @GetMapping("/ex01")
    public String ex01(Model model){
        model.addAttribute("data", "타임리프다.");
        return "thymeleaf/ex01";
    }
    @GetMapping("/ex02")
    public static String ex2(Model model){
        // 빌더 패턴을 사용해서 itemDto 생성
        ItemDto itemDto = ItemDto.builder()
                .itemNm("상품명1")
                .itemDetail("상품상세")
                .price(10000)
                .regTime(LocalDateTime.now())
                .build();
        // 모델로 전달
        model.addAttribute("item", itemDto);
        // 타일이프 뷰 페이지로 데이터가 전달
        return "thymeleaf/ex02";
    }

    @GetMapping("/ex03")
    public static String ex03(Model model){
        // 10개의 itemDto 가 담긴 목록만들기
        ArrayList<ItemDto> itemDtoList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            ItemDto itemDto = ItemDto.builder()
                    .itemNm("상품명" + i)
                    .itemDetail("상품상세" + i)
                    .price(10000 + i)
                    .regTime(LocalDateTime.now())
                    .build();
            itemDtoList.add(itemDto);
        }
        // 모델에 저장
        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleaf/ex03";
    }
    @GetMapping("/ex04")
    public static String ex04(Model model){
        // 10개의 itemDto 가 담긴 목록만들기
        ArrayList<ItemDto> itemDtoList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            ItemDto itemDto = ItemDto.builder()
                    .itemNm("상품명" + i)
                    .itemDetail("상품상세" + i)
                    .price(10000 + i)
                    .regTime(LocalDateTime.now())
                    .build();
            itemDtoList.add(itemDto);
        }
        // 모델에 저장
        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleaf/ex04";
    }
    @GetMapping("/ex05")
    public static String ex05(Model model){
        return "thymeleaf/ex05";
    }
    @GetMapping("/ex06")
    public static String ex06(Model model, String param1, String param2){
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "thymeleaf/ex06";
    }    @GetMapping("/ex07")
    public static String ex07(Model model){
        return "thymeleaf/ex07";
    }
}
