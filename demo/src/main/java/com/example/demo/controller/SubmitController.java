package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Submit;
import com.example.demo.entity.SubmitAndTagAndUserAndPicture;
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

    @GetMapping("/getAllSubmitAndTagAndUserAndPicture")
    public List<SubmitAndTagAndUserAndPicture> getAllSubmitAndTagAndUserAndPicture(
            @RequestParam Map<String, String> map) {
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<SubmitAndTagAndUserAndPicture> submitAndTagAndUserAndPicture = submitService
                .getAllSubmitAndTagAndUserAndPicture();
        int start = (page - 1) * count;
        int end = page * count > submitAndTagAndUserAndPicture.size() ? submitAndTagAndUserAndPicture.size()
                : page * count;
        return submitAndTagAndUserAndPicture.subList(start, end);
    }

    @GetMapping("/getSubmitCount")
    public int getSubmitCount() {
        return submitService.getSubmitCount();
    }

    @GetMapping("/getSubmitAndTagAndUserAndPictureByUserid")
    public List<SubmitAndTagAndUserAndPicture> getSubmitAndTagAndUserAndPictureByUserid(
            @RequestParam Map<String, String> map) {
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        int userid = Integer.parseInt(map.get("userid"));
        List<SubmitAndTagAndUserAndPicture> submitAndTagAndUserAndPicture = submitService
                .getSubmitAndTagAndUserAndPictureByUserid(userid);
        int start = (page - 1) * count;
        int end = page * count > submitAndTagAndUserAndPicture.size() ? submitAndTagAndUserAndPicture.size()
                : page * count;
        return submitAndTagAndUserAndPicture.subList(start, end);
    }

    @GetMapping("/getSubmitCountByUserid")
    public int getSubmitCountByUserid(@RequestParam Map<String, String> map) {
        int userid = Integer.parseInt(map.get("userid"));
        return submitService.getSubmitCountByUserid(userid);
    }

    @PostMapping("/modSubmit")
    public Submit modSubmit(@RequestParam Map<String, String> map) {
        int submitid = Integer.parseInt(map.get("submitid"));
        String sub_state = map.get("sub_state");
        String sub_time = map.get("sub_time");
        int tagid = Integer.parseInt(map.get("tagid"));
        return submitService.modSubmit(submitid, sub_state, sub_time, tagid);
    }
}
