package com.example.warehouse.model;

import javax.persistence.*;

@Entity
public class UserWarehouse {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    Warehouse warehouse;


}
