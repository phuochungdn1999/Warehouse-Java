package com.example.warehouse;

import com.example.warehouse.model.elastic.User;
import com.example.warehouse.repository.elaticsearch.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WarehouseApplication {

    public static void main(String[] args) {

        SpringApplication.run(WarehouseApplication.class, args);



    }

}
