package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Submit;

@Mapper
public interface SubmitMapper {
    @Insert("insert into submit (sub_state, sub_time, tagid) values (#{sub_state}, #{sub_time}, #{tagid})")
    void addSubmit(Submit submit);

    @Select("select * from submit")
    List<Submit> getAllSubmit();

    @Select("select * from submit where submitid=#{submitid}")
    Submit getSubmitByid(int submitid);

    @Select("select * from submit where tagid=#{tagid}")
    Submit getSubmitByTagid(int tagid);

    @Select("select last_insert_id()")
    int getLastInsert();

    @Update("update submit set sub_state=#{sub_state}, sub_time=#{sub_time}, tagid=#{tagid} where submitid=#{submitid}")
    void modSubmit(Submit submit);
}
