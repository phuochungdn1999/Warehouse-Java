package com.example.warehouse.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;
    private Long category_id;

    private String name;

    private String  note;

    private String image;

    @ManyToOne
    @JoinColumn(name="cat_id",nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    Set<WarehouseProduct> warehouseProducts;

}
