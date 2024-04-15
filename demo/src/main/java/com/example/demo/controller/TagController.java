package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Tag;
import com.example.demo.service.TagService;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping("/addTag")
    public Tag addTag(@RequestParam Map<String, String> map) {
        String tagname = map.get("tagname");
        return tagService.addTag(tagname);
    }
}
