package com.company;

import com.company.service.BillService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.company")
public class SplitwiseApplication {

    @Autowired
    private UserService userService;

    @Autowired
    private BillService billService;

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

}

