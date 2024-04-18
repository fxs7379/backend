package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Discussion;
import com.example.demo.mapper.DiscussionMapper;

@Service
public class DiscussionService {
    @Autowired
    private DiscussionMapper discussionMapper;

    public Discussion addDiscussion(String content, int userid, int tagid) {
        Discussion discussion = new Discussion(0, content, userid, tagid);
        discussionMapper.addDiscussion(discussion);
        int discussionId = discussionMapper.getLastInsert();
        discussion.setDiscussionid(discussionId);
        return discussion;
    }

    public void delDiscussionById(int discussionId) {
        discussionMapper.delDiscussionById(discussionId);
    }

    public void modDiscussion(int discussionId, String content, int userid, int tagid) {
        Discussion discussion = new Discussion(discussionId, content, userid, tagid);
        discussionMapper.modDiscussion(discussion);
    }

    public List<Discussion> getAllDiscussion() {
        return discussionMapper.getAllDiscussion();
    }

    public Discussion getDiscussionByid(int discussionid) {
        return discussionMapper.getDiscussionByid(discussionid);
    }

    public Discussion getDiscussionByTagid(int tagid) {
        return discussionMapper.getDiscussionByTagid(tagid);
    }

}
