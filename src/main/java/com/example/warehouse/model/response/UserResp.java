package com.example.warehouse.model.response;

import com.example.warehouse.model.UserHistory;
import com.example.warehouse.model.UserWarehouse;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

public class UserResp {
    private Long id;

    private String name;

    private  String phone;

    private String email;

    private String address;

    private String image;

    private Set<UserHistory> userHistories;

    private Set<UserWarehouse> userWarehouses;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<UserHistory> getUserHistories() {
        return userHistories;
    }

    public void setUserHistories(Set<UserHistory> userHistories) {
        this.userHistories = userHistories;
    }

    public Set<UserWarehouse> getUserWarehouses() {
        return userWarehouses;
    }

    public void setUserWarehouses(Set<UserWarehouse> userWarehouses) {
        this.userWarehouses = userWarehouses;
    }

    public UserResp(Long id, String name, String phone, String email, String address, String image, Set<UserHistory> userHistories, Set<UserWarehouse> userWarehouses) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.image = image;
        this.userHistories = userHistories;
        this.userWarehouses = userWarehouses;
    }

    public UserResp() {
    }
}
