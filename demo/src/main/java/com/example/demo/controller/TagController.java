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

    @GetMapping("/getTagAndPictureByRegion")
    public List<TagAndPicture> getTagAndPictureByRegion(@RequestParam Map<String, String> map) {
        String region = map.get("region");
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<TagAndPicture> tagAndPictureList = tagService.getTagAndPictureByRegion(region);
        int start = (page - 1) * count;
        int end = page * count > tagAndPictureList.size() ? tagAndPictureList.size() : page * count;
        return tagAndPictureList.subList(start, end);
    }

    @GetMapping("/getTagCountByRegion")
    public int getTagCountByRegion(@RequestParam Map<String, String> map) {
        String region = map.get("region");
        return tagService.getTagCountByRegion(region);
    }

    @GetMapping("/getAllTagCount")
    public int getAllTagCount() {
        return tagService.getAllTagCount();
    }

    @GetMapping("/getAllTagAndPicture")
    public List<TagAndPicture> getAllTagAndPicture(@RequestParam Map<String, String> map) {
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<TagAndPicture> tagAndPictureList = tagService.getAllTagAndPicture();
        int start = (page - 1) * count;
        int end = page * count > tagAndPictureList.size() ? tagAndPictureList.size() : page * count;
        return tagAndPictureList.subList(start, end);
    }

    @GetMapping("/getTagAndPictureById")
    public TagAndPicture getTagAndPictureById(@RequestParam Map<String, String> map) {
        int tagid = Integer.parseInt(map.get("tagid"));
        return tagService.getTagAndPictureById(tagid);
    }

    @PostMapping("/modTag")
    public Tag modTag(@RequestParam Map<String, String> map) {
        int tagid = Integer.parseInt(map.get("tagid"));
        String tagname = map.get("tagname");
        int userid = Integer.parseInt(map.get("userid"));
        String region = map.get("region");
        String result = map.get("result");
        String tem = map.get("tem");
        String hum = map.get("hum");
        String win_dir = map.get("win_dir");
        String win_speed = map.get("win_speed");
        String pressure = map.get("pressure");
        return tagService.modTag(tagid, tagname, userid, region, result, tem, hum, win_dir, win_speed, pressure);
    }

    @GetMapping("/classifyTag")
    public String classifyTag(@RequestParam Map<String, String> map) throws Exception {
        int tagid = Integer.parseInt(map.get("tagid"));
        return tagService.classifyTag(tagid);
    }

    @GetMapping("/getWeather")
    public Tag getWeather(@RequestParam Map<String, String> map) throws Exception {
        int tagid = Integer.parseInt(map.get("tagid"));
        return tagService.getWeather(tagid);
    }
}
