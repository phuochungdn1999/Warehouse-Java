package com.example.warehouse.controller;

import com.example.warehouse.model.BodyWarehouse;
import com.example.warehouse.model.City;
import com.example.warehouse.model.User;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.service.CityService;
import com.example.warehouse.service.UserService;
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
@RequestMapping(value = "/warehouses")
public class WarehouseController {

    private WarehouseService warehouseService;
    private CityService cityService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService,CityService cityService){
        this.warehouseService = warehouseService;
        this.cityService = cityService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Warehouse> findAllWarehouse(){
        List<Warehouse> warehousesList = warehouseService.findAllWarehouse();
        return  warehousesList;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable("id")Long id){
        Optional<Warehouse> warehouse = warehouseService.findById(id);

        if(!warehouse.isPresent()){
            return new ResponseEntity<>(warehouse.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(warehouse.get(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public  ResponseEntity<Warehouse> createWarehouse(@RequestBody BodyWarehouse warehouse, UriComponentsBuilder builder){
        Warehouse newWarehouse = new Warehouse(warehouse.getName(), warehouse.getAddress(),warehouse.getDescription(), warehouse.getImage() );
        Optional<City> city =  cityService.findById(warehouse.getCity());
        if(!city.isPresent())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        newWarehouse.setCity(new City(city.get().getId(),city.get().getName(),city.get().getDescription(),city.get().getImage()));
        warehouseService.save(newWarehouse);
        //warehouseService.save(warehouse);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/warehouses/{id}")
                .buildAndExpand(newWarehouse.getId()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Warehouse> updateUser(@PathVariable("id")Long id, @RequestBody Warehouse warehouse){
        Optional<Warehouse> currentWarehouse = warehouseService.findById(id);
        if(!currentWarehouse.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentWarehouse.get().setName(warehouse.getName());
        currentWarehouse.get().setImage(warehouse.getImage());
        currentWarehouse.get().setAddress(warehouse.getAddress());
        currentWarehouse.get().setDescription(warehouse.getDescription());

        warehouseService.save(currentWarehouse.get());
        return new ResponseEntity<>(currentWarehouse.get(), HttpStatus.OK);

    }

}
