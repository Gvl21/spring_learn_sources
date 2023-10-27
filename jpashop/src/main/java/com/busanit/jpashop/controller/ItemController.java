package com.busanit.jpashop.controller;

import com.busanit.jpashop.dto.ItemFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {


   @GetMapping("/admin/item/new")
    public String itemForm(Model model){

       model.addAttribute("itemFormDto", new ItemFormDto());


       return "item/itemForm";
   }
}
