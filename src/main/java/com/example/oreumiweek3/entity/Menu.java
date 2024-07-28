package com.example.oreumiweek3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false)
    @Setter
    private String category;

    @Column(nullable = false)
    @Setter
    private Double price;

    @Column(nullable = false)
    @Setter
    private String description;

    @OneToMany(mappedBy = "menu")
    private List<OrderItem> orderItems;

    public Menu(String name, String category, Double price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }
}
