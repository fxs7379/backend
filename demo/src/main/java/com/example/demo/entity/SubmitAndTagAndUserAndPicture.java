package com.example.demo.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitAndTagAndUserAndPicture {
    Submit submit;
    Tag tag;
    User user;
    List<Picture> pictureList;
}
