package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Tag;
import com.example.demo.entity.TagAndPicture;
import com.example.demo.service.TagService;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping("/addTag")
    public Tag addTag(@RequestParam Map<String, String> map) {
        String tagname = map.get("tagname");
        int userid = Integer.parseInt(map.get("userid"));
        String region = map.get("region");
        return tagService.addTag(tagname, userid, region);
    }

    @GetMapping("/getTagAndPictureByUserid")
    public List<TagAndPicture> getTagAndPictureByUserid(@RequestParam Map<String, String> map) {
        int userid = Integer.parseInt(map.get("userid"));
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<TagAndPicture> tagAndPictureList = tagService.getTagAndPictureByUserid(userid);
        int start = (page - 1) * count;
        int end = page * count > tagAndPictureList.size() ? tagAndPictureList.size() : page * count;
        return tagAndPictureList.subList(start, end);
    }

    @GetMapping("/getTagCountByUserid")
    public int getTagCountByUserid(@RequestParam Map<String, String> map) {
        int userid = Integer.parseInt(map.get("userid"));
        return tagService.getTagCountByUserid(userid);
    }
}
