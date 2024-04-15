package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Tag;
import com.example.demo.mapper.TagMapper;

@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;

    public Tag addTag(String tagname, int userid, String region) {
        Tag oldTag = tagMapper.getTagByName(tagname);
        if (oldTag != null) {
            return null;
        }
        Tag tag = new Tag(0, tagname, userid, region);
        tagMapper.addTag(tag);
        int tagid = tagMapper.getLastInsert();
        tag.setTagid(tagid);
        return tag;
    }
}
