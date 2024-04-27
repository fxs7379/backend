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
    public List<User> findAllStaff(@RequestParam Map<String, String> map) {
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<User> userList = userService.findAllStaff();
        int start = (page - 1) * count;
        int end = page * count > userList.size() ? userList.size() : page * count;
        return userList.subList(start, end);
    }

    @GetMapping("/getStaffCount")
    public int getStaffCount() {
        return userService.findAllStaff().size();
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

    @PostMapping("/addUser")
    public User addUser(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String phone_number = map.get("phone_number");
        String region = map.get("region");
        int onjob = Integer.parseInt(map.get("onjob"));
        int type = Integer.parseInt(map.get("type"));
        return userService.addUser(0, username, password, phone_number, region, onjob, type);
    }

    @PostMapping("/delUser")
    public void delUser(@RequestParam Map<String, String> map) {
        int userid = Integer.parseInt(map.get("userid"));
        userService.delUser(userid);
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam Map<String, String> map) {
        int userid = Integer.parseInt(map.get("userid"));
        return userService.getUserById(userid);
    }

    @GetMapping("/getAllRegion")
    public List<String> getAllRegion() {
        return userService.getAllRegion();
    }
}
