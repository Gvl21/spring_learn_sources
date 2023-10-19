package com.busanit.jpashop.entity;

import com.busanit.jpashop.constant.ItemSellStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String itemNm;          // 상품명
    private Integer price;          // 상품 가격
    private Integer stockNumber;    // 재고량
    private String itemDetail;      // 상품 상세
    private ItemSellStatus itemSellStatus;  // 판매 상태
    private LocalDateTime regTime;          // 등록시간
    private LocalDateTime updateTime;       // 수정시가
}
