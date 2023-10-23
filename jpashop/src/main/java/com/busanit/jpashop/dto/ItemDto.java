package com.busanit.jpashop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor      // 에러가 날 수 있다 생성자가 없으면
public class ItemDto {
    private Long id;
    private String itemNm;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

}
