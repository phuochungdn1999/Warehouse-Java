package com.example.warehouse.service.impl;

import com.example.warehouse.model.User;
import com.example.warehouse.model.UserPrincipal;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserPrincipal findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();


            userPrincipal.setUserId(user.getId());
            userPrincipal.setEmail(user.getEmail());
            userPrincipal.setPassword(user.getPassword());
//            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAllUser() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }


}
