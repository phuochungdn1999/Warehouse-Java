package com.example.warehouse.controller;

import com.example.warehouse.model.*;
import com.example.warehouse.model.response.UserResp;
import com.example.warehouse.service.UserService;
import com.example.warehouse.service.elasticsearch.UserSearchService;
import com.example.warehouse.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public  class UserController {

    private UserService userService;
    private UserSearchService userSearchService;
    private JwtUtil jwtUtil;
    private JavaMailSender javaMailSender;

    @Autowired
    public UserController(UserService userService,JwtUtil jwtUtil,UserSearchService userSearchService,JavaMailSender javaMailSender){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userSearchService = userSearchService;
        this.javaMailSender = javaMailSender;
    }



    @RequestMapping(method = RequestMethod.GET)
    public List<UserResp> findAllUser(){
        List<User> userList = userService.findAllUser();
        if(userList.isEmpty()){
//            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        List<UserResp> list = new ArrayList<>();
        try{
            userList.forEach(item->
                    list.add(new UserResp(item.getId(), item.getName(), item.getPhone(),item.getEmail(),item.getAddress(),item.getImage(),item.getUserHistories(),item.getUserWarehouses()))
            );
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return  list;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id")Long id){
        Optional<User> user = userService.findById(id);

        if(!user.isPresent()){
            return new ResponseEntity<>(user.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(),HttpStatus.OK);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public  ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder builder){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        System.out.println(user.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/users/{id}")
                .buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id")Long id, @RequestBody User user){
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        UserPrincipal userPrincipal = userService.findByEmail(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(userPrincipal.getPassword());
        if ( !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
//        token.setCreatedBy(userPrincipal.getUserId());
//        tokenService.createToken(token);
        return ResponseEntity.ok(token.getToken());
    }

    @GetMapping("/hello")
        @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity hello(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserPrincipal)principal).getEmail();
        return ResponseEntity.ok(email);
    }

    @PostMapping("/test")
    public ResponseEntity testIndex(@RequestBody com.example.warehouse.model.elastic.User user){
        try{
            userSearchService.createUserIndex(user);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.ok(user);
        }

    }
    @RequestMapping(value = "/search/brand/{brand}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListByBrand(@PathVariable("brand")String brand){
        SearchHits<com.example.warehouse.model.elastic.User> userSearchHits = userSearchService.findByBrand(brand);

        if(userSearchHits.isEmpty()){
            return new ResponseEntity<>(userSearchHits.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userSearchHits.get(),HttpStatus.OK);
    }
    @RequestMapping(value = "/search/name/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListByName(@PathVariable("name")String name){
        SearchHits<com.example.warehouse.model.elastic.User> userSearchHits = userSearchService.findByName(name);

        if(userSearchHits.isEmpty()){
            return new ResponseEntity<>(userSearchHits.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userSearchHits.get(),HttpStatus.OK);
    }

    @RequestMapping("/sendEmail")
    public String sendSimpleEmail() {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("phuochungdn1999@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.javaMailSender.send(message);

        return "Email Sent!";
    }



}
