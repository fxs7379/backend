package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userid;
    private String username;
    private String password;
    private String phone_number;
    private String region;
    private int onjob;
    private int type;

}
