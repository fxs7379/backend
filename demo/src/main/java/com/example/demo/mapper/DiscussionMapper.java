package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Discussion;

@Mapper
public interface DiscussionMapper {
    @Insert("insert into discussion (content, userid, tagid, time) values (#{content}, #{userid}, #{tagid}, #{time})")
    void addDiscussion(Discussion discussion);

    @Select("select * from discussion")
    List<Discussion> getAllDiscussion();

    @Select("select * from discussion where discussionid=#{discussionid}")
    Discussion getDiscussionByid(int discussionid);

    @Select("select * from discussion where tagid=#{tagid}")
    Discussion getDiscussionByTagid(int tagid);

    @Select("select last_insert_id()")
    int getLastInsert();

    @Update("update discussion set content=#{content}, userid=#{userid}, tagid=#{tagid} where discussionid=#{discussionid}")
    void modDiscussion(Discussion discussion);

    @Delete("delete from discussion where discussionid=#{discussionid}")
    void delDiscussionById(int discussionid);
}
