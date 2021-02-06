package com.example.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductHistory {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private Long productId;

    private Long historyId;

    private Long amount;


}
