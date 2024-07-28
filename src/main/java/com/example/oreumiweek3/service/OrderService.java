package com.example.oreumiweek3.service;

import com.example.oreumiweek3.domain.OrderDto;
import com.example.oreumiweek3.domain.OrderItemDto;
import com.example.oreumiweek3.entity.Order;
import com.example.oreumiweek3.entity.OrderItem;
import com.example.oreumiweek3.entity.OrderState;
import com.example.oreumiweek3.repository.OrderItemRepository;
import com.example.oreumiweek3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }
    
    @Transactional
    public OrderDto create(OrderDto dto) {
        Order order = dto.toEntity();
        Order saved = orderRepository.save(order);
        return OrderDto.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public List<OrderDto> get() {
        return orderRepository.findAll().stream().map(OrderDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Optional<OrderDto> get(Long id) {
        return orderRepository.findById(id).map(OrderDto::fromEntity);
    }

    @Transactional(readOnly = true)
    public List<Double> getSales(Long id) {
        return orderRepository.getSales(id);
    }

    @Transactional
    public Optional<OrderDto> updateState(Long id, OrderState state) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> {
            o.updateState(state);
            return OrderDto.fromEntity(orderRepository.save(o));
        });
    }

    @Transactional
    public Optional<OrderDto> addOrderItems(Long id, List<OrderItemDto> orderItemDtos) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> {
            List<OrderItem> orderItems = orderItemDtos.stream().map(OrderItemDto::toEntity).toList();
            orderItemRepository.saveAll(orderItems);
            Optional<Order> byId = orderRepository.findById(id);
            return OrderDto.fromEntity(byId.get());
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return orderRepository.findById(id).map(o -> {
            orderRepository.delete(o);
            return true;
        }).orElse(false);
    }
}
