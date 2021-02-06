package com.example.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Permission {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String permissionName;

    public Permission(Long id, String permissionName) {
        this.id = id;
        this.permissionName = permissionName;
    }

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
