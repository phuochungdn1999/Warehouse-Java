package com.example.warehouse.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String  description;

    private String image;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private Set<Product> products;

    public Category(Long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Category() {
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
}
