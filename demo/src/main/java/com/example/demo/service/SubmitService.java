package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Submit;
import com.example.demo.mapper.SubmitMapper;
import com.example.demo.mapper.TagMapper;

@Service
public class SubmitService {
    @Autowired
    SubmitMapper submitMapper;

    @Autowired
    TagMapper tagMapper;

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

    // public List<Submit> 
}
