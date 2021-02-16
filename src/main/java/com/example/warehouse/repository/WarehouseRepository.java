package com.example.warehouse.repository;

import com.example.warehouse.controller.WarehouseController;
import com.example.warehouse.model.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse,Long> {
}
