package com.example.demo.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionAndTagAndPictureAndUser {
    Discussion discussion;
    Tag tag;
    List<Picture> pictureList;
    User user;
}
