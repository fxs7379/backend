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

    @Select("select * from user where username=#{username};")
    User findUserByName(String username);

    @Update("update user set password=#{password} where username=#{username};")
    void modPassword(String username, String password);

    @Select("select * from user where type=0;")
    List<User> findAllStu();

    @Insert("insert into user (username, password, phone_number, region,onjob, type) values (#{username}, #{password}, #{phone_nbumber}, #{region}, #{onjob},#{type});")
    void addUser(User user);

    @Delete("delete from user where type=0;")
    void delAllStu();

    @Delete("delete from user where username=#{username};")
    void delUserByName(String user);

    @Update("update user set region=#{region} where username=#{username};")
    void modClassroom(String username, String region);

    @Update("update user set phone_number=#{phone_number} where username=#{username};")
    void modNumber(String username, String phone_number);
}
