package com.busanit.springmvc.dto;

import com.busanit.springmvc.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PizzaDto {
    private Long id;
    private String name;
    private String price;

    public Pizza toEntity(){
        return new Pizza(id, name, price);
    }

}
