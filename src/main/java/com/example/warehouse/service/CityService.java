package com.example.warehouse.service;


import com.example.warehouse.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> findAllCity();

    Optional<City> findById(Long id);

    void save(City city);

    void remove(City city);
}
