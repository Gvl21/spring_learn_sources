package com.busanit.jpashop.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 주문 상품 | order_item */

@Entity
@Table(name = "order_item")
@Getter @Setter
public class OrderItem extends BaseEntity {

    // 주문상품id
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    // 상품 가격
    @Column(name = "order_price")
    private Integer orderPrice;

    // 개수
    @Column(name = "count")
    private Integer count;

    // 다대일 연관관계 매핑
    // : 하나의 주문은 여러 주문상품을 가질 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // 다대일 : 하나의 상품은 여러번 주문상품으로 추가될 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());

        // 상품 엔티티에서 재고를 감소 (개수만큼)
        item.removeStock(count);
        return orderItem;
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    // 주문 취소 메소드 => 상품 재고 증가
    public void cancel(){
        this.getItem().addStock(count);
    }
}


