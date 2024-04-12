package com.example.demo.service;

import java.util.List;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.Sha256;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public List<User>findAllUsers(){
        return userMapper.findAllUser();
    }
    public User login(String username,String password){
        User user = userMapper.findUserByName(username);
        if(user == null){
            return null;
        }else if(user.getPassword().equals(password) == false){
            return null;
        }
        return user;
    }
    public User modPassword(String username, String oldpassword,String newpassword){
        User user = userMapper.findUserByName(username);
        if(user == null || user.getPassword().equals(oldpassword)== false){
            return null;
        }else{
            userMapper.modPassword(username, newpassword);
            return userMapper.findUserByName(username);
        }
    }
    public List<User> findAllStu(){
        return userMapper.findAllStu();
    }
    public User addUser(User user){
        User user_1 = userMapper.findUserByName(user.getUsername());
        if(user_1 != null){
            return null;
        }
        userMapper.addUser(user);
        return userMapper.findUserByName(user.getUsername());

    }
    public void delAllStu(){
        userMapper.delAllStu();
    }
    public void delUser(String username){
        userMapper.findUserByName(username);

    }
}
