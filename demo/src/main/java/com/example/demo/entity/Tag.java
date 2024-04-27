package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    int tagid;
    String tagname;
    int userid;
    String region;
    String result;
    String tem;
    String hum;
    String win_dir;
    String win_speed;
    String pressure;
    String time;
}
