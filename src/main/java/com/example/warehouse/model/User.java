package com.example.warehouse.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private  String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String address;

    private String image;

    @OneToMany(mappedBy = "user")
    private Set<UserHistory> userHistories;

    @OneToMany(mappedBy = "user")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public User() {
    }

    public User(Long id, String name, String phone, String email, String password, String address, String image, Set<UserHistory> userHistories, Set<UserWarehouse> userWarehouses) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.image = image;
        this.userHistories = userHistories;
        this.userWarehouses = userWarehouses;
    }
}
