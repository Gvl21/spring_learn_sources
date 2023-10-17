package com.busanit.springmvc.entity;

import com.busanit.springmvc.dto.CommentDto;
import com.busanit.springmvc.dto.PizzaDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Pizza {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String price;

    public void patch(PizzaDto dto){
        // 예외처리
        if (this.id != dto.getId()){
            throw new IllegalArgumentException("메뉴 수정 실패, 잘못된 ID입니다.");
        }
        // 객체 업데이트
        if(dto.getName() != null){
            this.name = dto.getName();
        }
        if(dto.getPrice() != null){
            this.price = dto.getPrice();

        }


    }
}
