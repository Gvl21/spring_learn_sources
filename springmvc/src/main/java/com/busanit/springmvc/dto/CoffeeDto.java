package com.busanit.springmvc.dto;

import com.busanit.springmvc.entity.Coffee;
import lombok.AllArgsConstructor;

@AllArgsConstructor     // 모든 필드를 파라미터로 받는 생성자
public class CoffeeDto {
    private Long id;
    private String name;
    private String price;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}