package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Tag;

@Mapper
public interface TagMapper {
    @Insert("insert into tag (tagname, userid, region, result) values (#{tagname}, #{userid}, #{region}, #{result})")
    void addTag(Tag tag);

    @Delete("delete from tag where tagid=#{tagid}")
    void delTag(int tagid);

    @Update("update tag set tagname=#{tagname}, userid=#{userid}, region=#{region}, result=#{result} where tagid=#{tagid}")
    void modTag(Tag tag);

    @Select("select * from tag")
    List<Tag> getAllTag();

    @Select("select * from tag where tagid=#{tagid}")
    Tag getTagById(int tagid);

    @Select("select * from tag where tagname=#{tagname}")
    Tag getTagByName(String tagname);

    @Select("select * from tag where userid=#{userid}")
    List<Tag> getTagByUserid(int userid);

    @Select("select last_insert_id()")
    int getLastInsert();
}
