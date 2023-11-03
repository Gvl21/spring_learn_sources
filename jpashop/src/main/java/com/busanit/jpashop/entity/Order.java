package com.busanit.jpashop.entity;

import com.busanit.jpashop.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/** 주문 | orders */

@Entity
@Table(name = "orders")  // SQL 문 order by 예약어로 order 사용 불가
@Getter @Setter
public class Order extends BaseEntity {

    // 주문 id
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 주문일자
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    // 주문상태
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // 다대일 관계 : 한명의 회원은 여러 번 주문할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 일대다 관계 (다대일 관계의 양방향)
    // 외래키를 가지고 있는 연관관계의 주인 엔티티를 참조하는 목록을 필드로 갖는다 (연관관계의 주인이 아님)
    // 연관관계의 주인을 mappedBy로 설정
    // 부모 엔티티(Order)의 영속성 상태를 자식 엔티티(OrderItem)에 전이하는 Cascade 옵션.
    // 일대다 : 하나의 주문이 여러개의 주문 상품을 가지므로 List 자료형으로 매핑
    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true,     // 고아 객체 삭제
            fetch = FetchType.LAZY
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    // 새로운 주문 생성
    public static Order CreateOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);                        // 회원 세팅
        for (OrderItem orderItem : orderItemList) {     // 주문 상품 목록 세팅
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);        // 주문상태 ORDER 세팅
        order.setOrderDate(LocalDateTime.now());        // 주문시간 세팅
        return order;
    }

    // 주문 정보를 담기
    private void addOrderItem(OrderItem orderItem) {
        // 양방향 참조 관계
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    public void cancelOrder() {
        // 주문상태 => 주문취소로 변경
        this.orderStatus = OrderStatus.CANCEL;
        // 모든 주문 상품의 재고를 원상 복귀
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
            // 엔티티로 재고 수량 변경 로직을 이전
//             orderItem.getItem().setStockNumber(orderItem.getCount() + orderItem.getItem().getStockNumber());
        }
    }
}

