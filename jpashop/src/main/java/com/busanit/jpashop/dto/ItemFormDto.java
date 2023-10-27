package com.busanit.jpashop.dto;

import com.busanit.jpashop.constant.ItemSellStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// 상품을 등록할 때 사용자로부터 입력받아 전달하는 데이터
@Getter @Setter
public class ItemFormDto {
    private Long id;
    @NotBlank(message = "필수 입력 값입니다.")
    private String itemNm;
    @NotNull(message = "필수 입력 값입니다.")
    private Integer price;
    @NotBlank(message = "필수 입력 값입니다.")
    private String itemDetail;
    private ItemSellStatus itemSellStatus;
    @NotNull(message = "필수 입력 값입니다.")
    private Integer stockNumber;
    // 상품 저장 후 이미지 정보를 저장하는 리스트
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    // 상품의 이미지 아이디를 저장하는 리스트
    private List<Long> itemImgIds = new ArrayList<>();
}