package com.example.oreumiweek3.domain;

import com.example.oreumiweek3.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link OrderItem}
 */
@AllArgsConstructor
@Getter
public class OrderItemDto implements Serializable {
    private final Long id;
    private final Long orderId;
    private final Long menuId;
    private final Integer menuOrderCount;

    public static OrderItemDto fromEntity(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getId(), orderItem.getOrderId(), orderItem.getMenuId(), orderItem.getMenuOrderCount());
    }

    public OrderItem toEntity() {
        return new OrderItem(orderId, menuId, menuOrderCount);
    }
}