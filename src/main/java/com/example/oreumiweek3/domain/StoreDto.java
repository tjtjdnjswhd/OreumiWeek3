package com.example.oreumiweek3.domain;

import com.example.oreumiweek3.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Store}
 */
@AllArgsConstructor
@Getter
public class StoreDto implements Serializable {
    private final Long id;
    private final String name;
    private final String address;
    private final String telNumber;

    public static StoreDto fromEntity(Store store) {
        return new StoreDto(store.getId(), store.getName(), store.getAddress(), store.getTelNumber());
    }

    public Store toEntity() {
        return new Store(name, address, telNumber);
    }

    public void updateEntity(Store s) {
        s.setName(name);
        s.setAddress(address);
        s.setTelNumber(telNumber);
    }
}