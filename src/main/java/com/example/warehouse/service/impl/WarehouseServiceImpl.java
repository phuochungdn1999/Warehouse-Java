package com.example.warehouse.service.impl;

import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl (WarehouseRepository warehouseRepository){
        this.warehouseRepository = warehouseRepository;
    }
    @Override
    public List<Warehouse> findAllWarehouse() {
        return (List<Warehouse>) warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> findById(Long id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public void save(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    @Override
    public void remove(Warehouse warehouse) {
        warehouseRepository.delete(warehouse);
    }
}
