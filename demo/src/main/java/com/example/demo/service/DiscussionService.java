package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Discussion;
import com.example.demo.entity.DiscussionAndTagAndPictureAndUser;
import com.example.demo.entity.Picture;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.mapper.DiscussionMapper;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.mapper.TagMapper;
import com.example.demo.mapper.UserMapper;

@Service
public class DiscussionService {
    @Autowired
    private DiscussionMapper discussionMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PictureMapper pictureMapper;

    public Discussion addDiscussion(String content, int userid, int tagid) {
        String time = String.valueOf(System.currentTimeMillis());
        Discussion discussion = new Discussion(0, content, userid, tagid, time);
        discussionMapper.addDiscussion(discussion);
        int discussionId = discussionMapper.getLastInsert();
        discussion.setDiscussionid(discussionId);
        return discussion;
    }

    public void delDiscussionById(int discussionId) {
        discussionMapper.delDiscussionById(discussionId);
    }

    public void modDiscussion(int discussionId, String content, int userid, int tagid, String time) {
        Discussion discussion = new Discussion(discussionId, content, userid, tagid, time);
        discussionMapper.modDiscussion(discussion);
    }

    public List<Discussion> getAllDiscussion() {
        return discussionMapper.getAllDiscussion();
    }

    public List<DiscussionAndTagAndPictureAndUser> getAllDiscussionAndTagAndPictureAndUser() {
        List<Discussion> discussionList = discussionMapper.getAllDiscussion();
        List<DiscussionAndTagAndPictureAndUser> discussionAndTagAndPictureAndUserList = new ArrayList<DiscussionAndTagAndPictureAndUser>();
        for (int i = 0; i < discussionList.size(); i++) {
            Discussion discussion = discussionList.get(i);
            Tag tag = tagMapper.getTagById(discussion.getTagid());
            User user = userMapper.findUserById(discussion.getUserid());
            List<Picture> pictureList = pictureMapper.getPictureByTagid(tag.getTagid());
            DiscussionAndTagAndPictureAndUser discussionAndTagAndPictureAndUser = new DiscussionAndTagAndPictureAndUser(
                    discussion, tag, pictureList, user);
            discussionAndTagAndPictureAndUserList.add(discussionAndTagAndPictureAndUser);
        }
        return discussionAndTagAndPictureAndUserList;
    }

    public Discussion getDiscussionByid(int discussionid) {
        return discussionMapper.getDiscussionByid(discussionid);
    }

    public Discussion getDiscussionByTagid(int tagid) {
        return discussionMapper.getDiscussionByTagid(tagid);
    }

}
