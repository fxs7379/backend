package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        User user = userMapper.findUserByName(username);
        if (user == null) {
            return null;
        } else if (user.getPassword().equals(password) == false) {
            return null;
        }
        return user;
    }

    public User getUserById(int userid) {
        return userMapper.findUserById(userid);
    }

    public User modUser(int userid, String username, String password, String phone_number, String region, int onjob,
            int type) {
        User user = new User(userid, username, password, phone_number, region, onjob, type);
        userMapper.modUser(user);
        return user;
    }

    public List<User> findAllStaff() {
        return userMapper.findAllStaff();
    }

    public User addUser(int userid, String username, String password, String phone_number, String region, int onjob,
            int type) {
        User user = new User(0, username, password, phone_number, region, onjob, type);
        User oldUser = userMapper.findUserByName(username);
        if (oldUser != null) {
            return null;
        }
        userMapper.addUser(user);
        user.setUserid(userMapper.getLastInsert());
        return user;
    }

    public void delUser(int userid) {
        userMapper.delUserById(userid);
    }
}
