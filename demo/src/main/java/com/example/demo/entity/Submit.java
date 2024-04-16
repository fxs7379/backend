package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submit {
    int submitid;
    String sub_state;
    String sub_time;
    int tagid;
}
