package com.example.warehouse.service;

import com.example.warehouse.model.User;
import com.example.warehouse.model.UserPrincipal;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser();

    Optional<User> findById(Long id);

    void save(User user);

    void remove(User user);

    UserPrincipal findByEmail(String email);
}
