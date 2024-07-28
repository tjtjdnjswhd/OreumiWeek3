package com.example.oreumiweek3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false)
    @Setter
    private String address;

    @Column(nullable = false)
    @Setter
    private String telNumber;

    @OneToMany(mappedBy = "store")
    private List<Order> orders;

    public Store(String name, String address, String telNumber) {
        this.name = name;
        this.address = address;
        this.telNumber = telNumber;
    }
}
