package com.busanit.jpashop.entity;

import com.busanit.jpashop.constant.ItemSellStatus;
import com.busanit.jpashop.dto.ItemFormDto;
import com.busanit.jpashop.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name="item")     // 테이블을 다른 이름 설정 가능
@Entity
@Getter @Setter @ToString
public class Item extends BaseEntity {
    @Id
    @Column(name="item_id")     // 컬럼명 다른 이름 설정 가능
    @GeneratedValue(strategy = GenerationType.AUTO)     // 자동생성 전략 선택 가능
    private Long id;

    @Column(nullable = false, length = 50)     // 필수(NN Not Null) 컬럼, 길이 설정 가능
    private String itemNm;       // 상품명

    @Column(nullable = false)
    private Integer price;       // 상품가격

    @Column(nullable = false)
    private Integer stockNumber; // 재고수량

    @Lob    // LargeObject BLOB(바이너리), CLOB(텍스트) 타입 매핑
    @Column(nullable = false)
    private String itemDetail;   // 상품상세

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  // 판매상태

    // 영속성 컨텍스트 변경감지기능 활용 : 트랜잭션 종료시 업데이트 쿼리 수행
    public void updateItem(ItemFormDto itemFormDto) {
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    // 주문 발생시 재고 감소 메소드
    public void removeStock(int count) {

        int rest = this.stockNumber- count;
        // 개수 부족시 예외발생
        if (rest < 0) throw new OutOfStockException("상품의 재고가 부족합니다.");
        this.stockNumber = rest;
    }
    // 주문 취소시 재고 증가 메소드
    public void addStock(int count){
        this.stockNumber += count;

    }

    // Auditing 추가로 삭제
//    private LocalDateTime regTime;      // 등록시간
//    private LocalDateTime updateTime;   // 수정시간
    /*
    // 다대다 관계 예시
    // 연결 테이블에 다른 컬럼을 추가할 수가 없다. => 실무에서 사용하지 않음
    // 다대일 관계로 연결된 테이블용 엔티티를 따로 생성해서 매핑하는 것이 좋다.
    @ManyToMany
    @JoinTable(
            name = "member_item",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Member> members;
    */



}
