package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.City;

@Mapper
public interface CityMapper {
    @Select("select * from city where location_name=#{location_name}")
    City getCityByName(String location_name);
}
