package com.busanit.jpashop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDto {

    // 상품 ID
    @NotNull(message = "상품 아이디는 필수 입력값입니다.")
    private Long itemId;

    // 수량
    @Min(value = 1, message = "최소 주문 수량은 1개입니다.")
    @Max(value = 999, message = "최소 주문 수량은 999개입니다.")
    private int count;


}
