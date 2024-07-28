package com.example.oreumiweek3.controller;

import com.example.oreumiweek3.domain.MenuDto;
import com.example.oreumiweek3.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuDto> get(@RequestParam(value = "category", required = false) String category) {
        return category == null ? menuService.get() : menuService.getByCategory(category);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MenuDto> getById(@PathVariable(name = "id") Long id) {
        Optional<MenuDto> dto = menuService.get(id);
        return ResponseEntity.of(dto);
    }

    @GetMapping(path = "/top-three")
    public List<MenuDto> getTopThreeMenu() {
        return menuService.getTopByOrderCount(3);
    }

    @PostMapping
    public ResponseEntity<MenuDto> create(@RequestBody MenuDto dto) {
        return new ResponseEntity<>(menuService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MenuDto> update(@PathVariable("id") Long id, @RequestBody MenuDto dto) {
        return ResponseEntity.of(menuService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return menuService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
