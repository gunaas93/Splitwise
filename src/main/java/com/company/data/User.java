package com.company.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by manishsharma on 10/03/18.
 */
@Data
public class User {

    private int id;

    private String name;

    private String email;

    private double balance;

    private Date createdTime;

    public User(String name) {
        this.name = name;
        createdTime = new Date();
    }

}
