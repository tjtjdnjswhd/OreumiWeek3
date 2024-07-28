package com.example.oreumiweek3.repository;

import com.example.oreumiweek3.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(oi.menu.price * oi.menuOrderCount) FROM OrderItem oi WHERE oi.orderId = :id")
    List<Double> getSales(@Param("id") Long id);
}