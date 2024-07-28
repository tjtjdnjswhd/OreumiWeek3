package com.example.oreumiweek3.repository;

import com.example.oreumiweek3.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCategory(String category);

    @Query(
            """
            SELECT oi.menu FROM OrderItem oi
            GROUP BY oi.menu.id
            ORDER BY SUM(COUNT(*)) * oi.menuOrderCount
            LIMIT :count
            """)
    List<Menu> findTopByOrderCount(@Param("count") int count);
}