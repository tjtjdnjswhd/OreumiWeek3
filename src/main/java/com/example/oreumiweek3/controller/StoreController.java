package com.example.oreumiweek3.controller;

import com.example.oreumiweek3.domain.StoreDto;
import com.example.oreumiweek3.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<StoreDto> getAll() {
        return storeService.get();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StoreDto> getById(@PathVariable(name = "id") Long id) {
        Optional<StoreDto> dto = storeService.get(id);
        return ResponseEntity.of(dto);
    }

    @GetMapping(path = "/{id}/sales")
    public ResponseEntity<Double> getSalesByOrderDoneAt(@PathVariable(name = "id") Long id, @RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        Optional<Double> result = storeService.getSalesByOrderDoneAt(id, start, end);
        return ResponseEntity.of(result);
    }

    @PostMapping
    public ResponseEntity<StoreDto> create(@RequestBody StoreDto dto) {
        return new ResponseEntity<>(storeService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StoreDto> update(@PathVariable("id") Long id, @RequestBody StoreDto dto) {
        return ResponseEntity.of(storeService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return storeService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
