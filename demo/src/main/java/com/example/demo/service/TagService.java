package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Picture;
import com.example.demo.entity.Tag;
import com.example.demo.entity.TagAndPicture;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.mapper.TagMapper;

@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;

    @Autowired
    PictureMapper pictureMapper;

    public Tag addTag(String tagname, int userid, String region) {
        Tag oldTag = tagMapper.getTagByName(tagname);
        if (oldTag != null) {
            return null;
        }
        Tag tag = new Tag(0, tagname, userid, region, "");
        tagMapper.addTag(tag);
        int tagid = tagMapper.getLastInsert();
        tag.setTagid(tagid);
        return tag;
    }

    public List<TagAndPicture> getTagAndPictureByUserid(int userid) {
        List<Tag> tagList = tagMapper.getTagByUserid(userid);
        List<TagAndPicture> tagAndPictureList = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            List<Picture> pictureList = pictureMapper.getPictureByTagid(tag.getTagid());
            TagAndPicture tagAndPicture = new TagAndPicture(tag, pictureList);
            tagAndPictureList.add(tagAndPicture);
        }
        return tagAndPictureList;
    }

    public int getTagCountByUserid(int userid) {
        return tagMapper.getTagByUserid(userid).size();
    }

    public Tag modTag(int tagid, String tagname, int userid, String region, String result) {
        Tag tag = new Tag(tagid, tagname, userid, region, result);
        tagMapper.modTag(tag);
        return tag;
    }

    public TagAndPicture getTagAndPictureById(int tagid) {
        Tag tag = tagMapper.getTagById(tagid);
        List<Picture> pictureList = pictureMapper.getPictureByTagid(tagid);
        return new TagAndPicture(tag, pictureList);
    }

    public List<TagAndPicture> getAllTagAndPicture() {
        List<Tag> tagList = tagMapper.getAllTag();
        List<TagAndPicture> tagAndPictureList = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            List<Picture> pictureList = pictureMapper.getPictureByTagid(tag.getTagid());
            TagAndPicture tagAndPicture = new TagAndPicture(tag, pictureList);
            tagAndPictureList.add(tagAndPicture);
        }
        return tagAndPictureList;
    }

    public int getAllTagCount() {
        return tagMapper.getAllTag().size();
    }
}
