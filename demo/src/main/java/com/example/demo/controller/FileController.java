package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Picture;
import com.example.demo.service.FileService;

@RestController
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/getFile")
    public ResponseEntity<byte[]> getFile(@RequestParam Map<String, String> map) throws Exception {
        String fileName = map.get("fileName");
        return fileService.getFile(fileName);
    }

    @PostMapping("/uploadFile")
    public Picture uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("tagid") int tagid)
            throws Exception {
        return fileService.uploadFile(file, tagid);
    }
}
