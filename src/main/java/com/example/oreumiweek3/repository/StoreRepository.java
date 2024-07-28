package com.example.oreumiweek3.repository;

import com.example.oreumiweek3.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("""
            SELECT SUM(oi.menuOrderCount * oi.menu.price)
            FROM Store s
            INNER JOIN s.orders o
            INNER JOIN o.orderItems oi
            WHERE s.id = :storeId AND o.doneAt BETWEEN :start AND :end""")
    Optional<Double> getSalesByOrderDoneAtBetween(@Param("storeId") Long storeId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}