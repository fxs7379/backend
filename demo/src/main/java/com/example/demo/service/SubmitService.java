package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Picture;
import com.example.demo.entity.Submit;
import com.example.demo.entity.SubmitAndTagAndUserAndPicture;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.mapper.SubmitMapper;
import com.example.demo.mapper.TagMapper;
import com.example.demo.mapper.UserMapper;

@Service
public class SubmitService {
    @Autowired
    SubmitMapper submitMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    UserMapper userMapper;

    public Submit addSubmit(String sub_state, String sub_time, int tagid) {
        Submit oldSubmit = submitMapper.getSubmitByTagid(tagid);
        if (oldSubmit != null) {
            return null;
        }
        Submit submit = new Submit(0, sub_state, sub_time, tagid);
        submitMapper.addSubmit(submit);
        int submitid = submitMapper.getLastInsert();
        submit.setSubmitid(submitid);
        return submit;
    }

    public Submit getSubmitByTagid(int tagid) {
        Submit submit = submitMapper.getSubmitByTagid(tagid);
        return submit;
    }

    public List<SubmitAndTagAndUserAndPicture> getAllSubmitAndTagAndUserAndPicture() {
        List<SubmitAndTagAndUserAndPicture> submitAndTagAndUserAndPicture = new ArrayList<>();
        List<Submit> submitList = submitMapper.getAllSubmit();
        for (int i = 0; i < submitList.size(); i++) {
            Submit submit = submitList.get(i);
            Tag tag = tagMapper.getTagById(submit.getTagid());
            User user = userMapper.findUserById(tag.getUserid());
            List<Picture> pictureList = pictureMapper.getPictureByTagid(tag.getTagid());
            submitAndTagAndUserAndPicture.add(new SubmitAndTagAndUserAndPicture(submit, tag, user, pictureList));
        }
        return submitAndTagAndUserAndPicture;
    }

    public int getSubmitCount() {
        return submitMapper.getAllSubmit().size();
    }

    public List<SubmitAndTagAndUserAndPicture> getSubmitAndTagAndUserAndPictureByUserid(int userid) {
        List<SubmitAndTagAndUserAndPicture> submitAndTagAndUserAndPictureList = new ArrayList<>();
        List<Tag> tagList = tagMapper.getTagByUserid(userid);
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            Submit submit = submitMapper.getSubmitByTagid(tag.getTagid());
            if (submit == null) {
                continue;
            }
            List<Picture> pictureList = pictureMapper.getPictureByTagid(tag.getTagid());
            User user = userMapper.findUserById(tag.getUserid());
            submitAndTagAndUserAndPictureList.add(new SubmitAndTagAndUserAndPicture(submit, tag, user, pictureList));
        }
        return submitAndTagAndUserAndPictureList;
    }

    public int getSubmitCountByUserid(int userid) {
        int count = 0;
        List<Tag> tagList = tagMapper.getTagByUserid(userid);
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            Submit submit = submitMapper.getSubmitByTagid(tag.getTagid());
            if (submit == null) {
                continue;
            }
            count++;
        }
        return count;
    }

    public Submit modSubmit(int submitid, String sub_state, String sub_time, int tagid) {
        Submit submit = new Submit(submitid, sub_state, sub_time, tagid);
        submitMapper.modSubmit(submit);
        return submit;
    }
}
