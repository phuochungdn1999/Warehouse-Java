package com.example.warehouse.controller;

import com.example.warehouse.model.City;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.service.CityService;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/cities")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<City> findAllWarehouse(){
        List<City> warehousesList = cityService.findAllCity();
        return  warehousesList;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> getCityById(@PathVariable("id")Long id){
        Optional<City> city = cityService.findById(id);

        if(!city.isPresent()){
            return new ResponseEntity<>(city.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(city.get(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public  ResponseEntity<City> createCity(@RequestBody City city, UriComponentsBuilder builder){
        cityService.save(city);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/warehouses/{id}")
                .buildAndExpand(city.getId()).toUri());
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }


}
