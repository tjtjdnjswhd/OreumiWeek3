package com.example.oreumiweek3.controller;

import com.example.oreumiweek3.domain.OrderDto;
import com.example.oreumiweek3.entity.OrderState;
import com.example.oreumiweek3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.get();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable(name = "id") Long id) {
        Optional<OrderDto> dto = orderService.get(id);
        return ResponseEntity.of(dto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto dto) {
        return new ResponseEntity<>(orderService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}/state")
    public ResponseEntity<OrderDto> updateState(@PathVariable("id") Long id, @RequestParam OrderState orderState) {
        Optional<OrderDto> optional = orderService.updateState(id, orderState);
        return ResponseEntity.of(optional.map(o -> {
            if (orderState.equals(OrderState.DONE)) {
                List<Double> sales = orderService.getSales(id);
                o.setSales(sales.stream().reduce(0.0, Double::sum));
            }
            return o;
        }));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return orderService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
