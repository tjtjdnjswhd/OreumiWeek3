package com.example.oreumiweek3.service;

import com.example.oreumiweek3.domain.StoreDto;
import com.example.oreumiweek3.entity.Store;
import com.example.oreumiweek3.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public StoreDto create(StoreDto dto) {
        Store store = dto.toEntity();
        Store saved = storeRepository.save(store);
        return StoreDto.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public List<StoreDto> get() {
        return storeRepository.findAll().stream().map(StoreDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Optional<StoreDto> get(Long id) {
        return storeRepository.findById(id).map(StoreDto::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<Double> getSalesByOrderDoneAt(Long id, LocalDateTime start, LocalDateTime end) {
        return storeRepository.getSalesByOrderDoneAtBetween(id, start, end);
    }

    @Transactional
    public Optional<StoreDto> update(Long id, StoreDto dto) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(s -> {
            dto.updateEntity(s);
            return StoreDto.fromEntity(storeRepository.save(s));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return storeRepository.findById(id).map(s -> {
            storeRepository.delete(s);
            return true;
        }).orElse(false);
    }
}
