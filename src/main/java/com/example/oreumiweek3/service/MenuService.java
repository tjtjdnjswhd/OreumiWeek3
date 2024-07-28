package com.example.oreumiweek3.service;

import com.example.oreumiweek3.domain.MenuDto;
import com.example.oreumiweek3.domain.StoreDto;
import com.example.oreumiweek3.entity.Menu;
import com.example.oreumiweek3.entity.Store;
import com.example.oreumiweek3.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional
    public MenuDto create(MenuDto dto) {
        Menu menu = dto.toEntity();
        Menu saved = menuRepository.save(menu);
        return MenuDto.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public List<MenuDto> get() {
        return menuRepository.findAll().stream().map(MenuDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Optional<MenuDto> get(Long id) {
        return menuRepository.findById(id).map(MenuDto::fromEntity);
    }

    @Transactional(readOnly = true)
    public List<MenuDto> getByCategory(String category) {
        return menuRepository.findByCategory(category).stream().map(MenuDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public List<MenuDto> getTopByOrderCount(int count) {
        return menuRepository.findTopByOrderCount(count).stream().map(MenuDto::fromEntity).toList();
    }

    @Transactional
    public Optional<MenuDto> update(Long id, MenuDto dto) {
        Optional<Menu> menu = menuRepository.findById(id);
        return menu.map(m -> {
            dto.updateEntity(m);
            return MenuDto.fromEntity(menuRepository.save(m));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return menuRepository.findById(id).map(m -> {
            menuRepository.delete(m);
            return true;
        }).orElse(false);
    }
}
