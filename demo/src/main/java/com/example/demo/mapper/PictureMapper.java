package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Picture;

@Mapper
public interface PictureMapper {
    @Insert("insert into picture (picture_path, tagid) values (#{picture_path}, #{tagid})")
    void addPicture(Picture picture);

    @Delete("delete from picture where pictureid=#{pictureid}")
    void delPicture(int pictureid);

    @Select("select * from picture where tagid=#{tagid}")
    List<Picture> getPictureByTagid(int tagid);

    @Select("select * from picture where pictureid=#{pictureid}")
    Picture getPictureByid(int pictureid);

    @Select("select last_insert_id()")
    int getLastInsert();
}
