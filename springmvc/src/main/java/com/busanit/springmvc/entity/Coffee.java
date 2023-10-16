package com.busanit.springmvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Coffee {
    @Id @GeneratedValue
    Long id;
    @Column
    String name;
    @Column
    String price;

    public void patch(Coffee coffee) {
        // 수정 DTO -> Entity, name 필드가 있으면
        if (coffee.name != null) {
            name = coffee.name;
        } // 수정 DTO -> Entity, price 필드가 있으면
        if (coffee.price != null) {
            price = coffee.price;
        }

    }
}
