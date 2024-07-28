package com.example.oreumiweek3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "order_id", insertable = false, updatable = false)
    private Long orderId;

    @Column(nullable = false, name = "menu_id", insertable = false, updatable = false)
    private Long menuId;

    /**
     * @apiNote N개 주문
     */
    @Column(nullable = false)
    @Setter
    private Integer menuOrderCount;

    @JoinColumn(name = "order_id")
    @ManyToOne(optional = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public OrderItem(Long orderId, Long menuId, Integer menuOrderCount) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.menuOrderCount = menuOrderCount;
    }
}
