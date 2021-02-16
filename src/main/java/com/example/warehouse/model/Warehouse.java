package com.example.warehouse.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String name;

    private String address;

    private String description;

    private String image;

    public Warehouse(String name, String address, String description, String image) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.image = image;
    }

    @ManyToOne
    @JoinColumn(name="id_city",nullable = false)
    private City city;

    @OneToMany(mappedBy = "warehouse")
    private Set<WarehouseProduct> warehouseProducts;

    @OneToMany(mappedBy = "warehouse")
    private Set<UserWarehouse> userWarehouses;

    public Warehouse(Long id, String name, String address, String description, String image, City city, Set<WarehouseProduct> warehouseProducts, Set<UserWarehouse> userWarehouses) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.image = image;
        this.city = city;
        this.warehouseProducts = warehouseProducts;
        this.userWarehouses = userWarehouses;
    }

    public Warehouse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<WarehouseProduct> getWarehouseProducts() {
        return warehouseProducts;
    }

    public void setWarehouseProducts(Set<WarehouseProduct> warehouseProducts) {
        this.warehouseProducts = warehouseProducts;
    }

    public Set<UserWarehouse> getUserWarehouses() {
        return userWarehouses;
    }

    public void setUserWarehouses(Set<UserWarehouse> userWarehouses) {
        this.userWarehouses = userWarehouses;
    }
}
