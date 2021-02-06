package com.example.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserPermission {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private Long userId;

    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public UserPermission(Long id, Long userId, Long permissionId) {
        this.id = id;
        this.userId = userId;
        this.permissionId = permissionId;
    }

    public UserPermission() {
    }
}
