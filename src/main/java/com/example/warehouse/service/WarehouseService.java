package com.example.warehouse.service;

import com.example.warehouse.model.User;
import com.example.warehouse.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    List<Warehouse> findAllWarehouse();

    Optional<Warehouse> findById(Long id);

    void save(Warehouse warehouse);

    void remove(Warehouse warehouse);
}
