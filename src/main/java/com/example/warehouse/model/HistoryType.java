package com.example.warehouse.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class HistoryType {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String name;

    private String  description;

    @OneToMany(mappedBy = "historyType",fetch = FetchType.LAZY)
    private Set<History> histories;

    public HistoryType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public HistoryType() {
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
}
