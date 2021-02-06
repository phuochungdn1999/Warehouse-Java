package com.example.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PermissionDetail {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private Long permissionId;

    private String  actionCode;

    private String actionName;

    @Column(columnDefinition = "boolean default false")
    private boolean checkAction;

    public PermissionDetail(Long id, Long permissionId, String actionCode, String actionName, boolean checkAction) {
        this.id = id;
        this.permissionId = permissionId;
        this.actionCode = actionCode;
        this.actionName = actionName;
        this.checkAction = checkAction;
    }

    public PermissionDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public boolean isCheckAction() {
        return checkAction;
    }

    public void setCheckAction(boolean checkAction) {
        this.checkAction = checkAction;
    }
}
