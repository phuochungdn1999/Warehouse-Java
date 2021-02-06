package com.example.warehouse.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private Long cityId;

    private String name;

    private String address;

    private String description;

    private String image;

    @ManyToOne
    @JoinColumn(name="id_city",nullable = false)
    private City city;

    @OneToMany(mappedBy = "warehouse")
    Set<WarehouseProduct> warehouseProducts;

    @OneToMany(mappedBy = "warehouse")
    private Set<UserWarehouse> userWarehouses;
}
