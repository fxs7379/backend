package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Submit;
import com.example.demo.service.SubmitService;

@RestController
public class SubmitController {
    @Autowired
    SubmitService submitService;

    @PostMapping("/addSubmit")
    public Submit addSubmit(@RequestParam Map<String, String> map) {
        String sub_time = String.valueOf(System.currentTimeMillis());
        String sub_state = map.get("sub_state");
        int tagid = Integer.parseInt(map.get("tagid"));
        return submitService.addSubmit(sub_state, sub_time, tagid);
    }

    @GetMapping("/getSubmitByTagid")
    public Submit getSubmitByTagid(@RequestParam Map<String, String> map) {
        int tagid = Integer.parseInt(map.get("tagid"));
        return submitService.getSubmitByTagid(tagid);
    }
}
