package com.example.oreumiweek3.entity;

import com.example.oreumiweek3.repository.OrderItemRepository;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state = OrderState.ORDERED;

    @Column
    private LocalDateTime doneAt;

    @Column(nullable = false, name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

    @Column(nullable = false, name = "store_id", insertable = false, updatable = false)
    private Long storeId;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    @JoinColumn(name = "customer_id")
    @ManyToOne(optional = false)
    private Customer customer;

    @JoinColumn(name = "store_id")
    @ManyToOne(optional = false)
    private Store store;

    public Order(Long customerId, Long storeId) {
        this.customerId = customerId;
        this.storeId = storeId;
    }

    public Order(OrderState state, LocalDateTime doneAt, Long customerId, Long storeId) {
        this(customerId, storeId);
        this.state = state;
        this.doneAt = doneAt;
    }

    public void updateState(OrderState state) {
        if (this.state.equals(OrderState.DONE)) {
            throw new IllegalArgumentException("Can not update state if done");
        }

        this.state = state;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    @PreUpdate
    protected void updateDoneAt() {
        if (this.state.equals(OrderState.ORDERED)) {
            this.doneAt = LocalDateTime.now();
        }
    }
}
