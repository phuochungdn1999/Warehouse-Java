package com.example.warehouse.service.impl;

import com.example.warehouse.model.City;
import com.example.warehouse.repository.CityRepository;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl (CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }
    @Override
    public List<City> findAllCity() {
        return (List<City>)cityRepository.findAll() ;
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void remove(City city) {
        cityRepository.delete(city);
    }
}
