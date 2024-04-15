package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {
    @Select("select * from user;")
    List<User> findAllUser();

    @Select("select * from user where type=0")
    List<User> findAllStaff();

    @Select("select * from user where username=#{username};")
    User findUserByName(String username);

    @Insert("insert into user (username, password, phone_number, region,onjob, type) values (#{username}, #{password}, #{phone_number}, #{region}, #{onjob},#{type});")
    void addUser(User user);

    @Delete("delete from user where username=#{username};")
    void delUserByName(String user);

    @Update("update user set username=#{username}, password=#{password}, phone_number=#{phone_number}, region=#{region}, onjob=#{onjob}, type=#{type} where userid=#{userid}")
    void modUser(User user);

    @Select("select last_insert_id()")
    int getLastInsert();

    @Delete("delete from user where userid=#{userid}")
    void delUserById(int userid);
}
