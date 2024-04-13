package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        return userService.login(username, password);
    }

    @GetMapping("/findAllStaff")
    public List<User> findAllStaff() {
        return userService.findAllStaff();
    }

    @PostMapping("/modUser")
    public User modUser(@RequestParam Map<String, String> map) {
        int userid = Integer.parseInt(map.get("userid"));
        String username = map.get("username");
        String password = map.get("password");
        String phone_number = map.get("phone_number");
        String region = map.get("region");
        int onjob = Integer.parseInt(map.get("onjob"));
        int type = Integer.parseInt(map.get("type"));
        return userService.modUser(userid, username, password, phone_number, region, onjob, type);
    }
}
