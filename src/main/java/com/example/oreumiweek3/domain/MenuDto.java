package com.example.oreumiweek3.domain;

import com.example.oreumiweek3.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link Menu}
 */
@AllArgsConstructor
@Getter
public class MenuDto implements Serializable {
    private final Long id;
    private final String name;
    private final String category;
    private final Double price;
    private final String description;

    public static MenuDto fromEntity(Menu menu) {
        return new MenuDto(menu.getId(), menu.getName(), menu.getCategory(), menu.getPrice(), menu.getDescription());
    }

    public Menu toEntity() {
        return new Menu(name, category, price, description);
    }

    public void updateEntity(Menu menu) {
        menu.setName(name);
        menu.setCategory(category);
        menu.setPrice(price);
        menu.setDescription(description);
    }
}