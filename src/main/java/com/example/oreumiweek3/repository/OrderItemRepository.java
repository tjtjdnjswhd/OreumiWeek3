package com.example.oreumiweek3.repository;

import com.example.oreumiweek3.domain.OrderItemDto;
import com.example.oreumiweek3.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
