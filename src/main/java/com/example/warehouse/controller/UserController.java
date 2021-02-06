package com.example.warehouse.controller;

import com.example.warehouse.model.User;
import com.example.warehouse.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public  class UserController {



    private UserService userService;

    @Autowired
    public  UserController (UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUser(){
        List<User> userList = userService.findAllUser();
        if(userList.isEmpty()){
            //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  userList;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id")Long id){
        Optional<User> user = userService.findById(id);

        if(!user.isPresent()){
            return new ResponseEntity<>(user.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public  ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder builder){
        userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/users/{id}")
                .buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id")Long id,@RequestBody User user){
        Optional<User> currentUser = userService.findById(id);
        if(!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentUser.get().setName(user.getName());
        currentUser.get().setImage(user.getImage());
        currentUser.get().setEmail(user.getEmail());
        currentUser.get().setPhone(user.getPhone());

        userService.save(currentUser.get());
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);

    }

}
