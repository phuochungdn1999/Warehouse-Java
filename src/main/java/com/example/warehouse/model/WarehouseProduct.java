package com.example.warehouse.model;

import javax.persistence.*;

@Entity
public class WarehouseProduct {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private  Long stock;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Long getId() {
        return id;
    }


}
