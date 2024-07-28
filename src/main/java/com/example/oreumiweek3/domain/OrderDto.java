package com.example.oreumiweek3.domain;

import com.example.oreumiweek3.entity.Order;
import com.example.oreumiweek3.entity.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@AllArgsConstructor
@Getter
public class OrderDto implements Serializable {
    private final Long id;
    private final OrderState state;
    private final LocalDateTime doneAt;
    private final Long customerId;
    private final Long storeId;
    private final List<OrderItemDto> orderItems;

    @Setter
    private double sales;

    public OrderDto(Long id, OrderState state, LocalDateTime doneAt, Long customerId, Long storeId, List<OrderItemDto> orderItems) {
        this.id = id;
        this.state = state;
        this.doneAt = doneAt;
        this.customerId = customerId;
        this.storeId = storeId;
        this.orderItems = orderItems;
    }

    public static OrderDto fromEntity(Order order) {
        return new OrderDto(order.getId(), order.getState(), order.getDoneAt(), order.getCustomerId(), order.getStoreId(), order.getOrderItems().stream().map(OrderItemDto::fromEntity).toList());
    }

    public Order toEntity() {
        Order order = new Order(state, doneAt, customerId, storeId);
        orderItems.forEach(oi -> order.addOrderItem(oi.toEntity()));
        return order;
    }
}