package com.busanit.jpashop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "item_img")
@Getter @Setter
public class ItemImg extends BaseEntity {

    // 상품이미지id
    @Id
    @GeneratedValue
    @Column(name = "item_img_id")
    private String itemImgId;

    // 이미지 파일 이름
    @Column
    private String imgName;

    // 원본 파일 이름
    @Column
    private String oriImgName;

    // 이미지 URL
    @Column
    private String imgUrl;

    // 대표 이미지 여부
    @Column
    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

}
