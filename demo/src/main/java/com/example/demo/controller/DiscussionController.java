package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Discussion;
import com.example.demo.service.DiscussionService;

@RestController
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @PostMapping("/addDiscussion")
    public Discussion addDiscussion(@RequestParam Map<String, String> map) {
        String content = map.get("content");
        int userid = Integer.parseInt(map.get("userid"));
        int tagid = Integer.parseInt(map.get("tagid"));
        return discussionService.addDiscussion(content, userid, tagid);
    }

    @GetMapping("/getAllDiscussion")
    public List<Discussion> getAllDiscussion() {
        return discussionService.getAllDiscussion();
    }
}
