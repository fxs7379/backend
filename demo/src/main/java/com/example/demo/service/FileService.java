package com.example.demo.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Picture;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.mapper.TagMapper;

@Service
public class FileService {
    private static String filePath = "/home/mdd/文档/fxs毕设/图片/";

    @Autowired
    TagMapper tagMapper;

    @Autowired
    PictureMapper pictureMapper;

    public ResponseEntity<byte[]> getFile(String fileName) throws Exception {
        File file = new File(filePath + fileName);
        byte[] content = Files.readAllBytes(file.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // 修改为适合的图片类型
                .body(content);
    }

    public Picture uploadFile(MultipartFile file, int tagid) throws Exception {
        String fileName = UUID.randomUUID().toString() + ".jpg";
        String tagname = tagMapper.getTagById(tagid).getTagname();
        Path folderPath = Paths.get(filePath, tagname);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        Path targetPath = folderPath.resolve(fileName);
        Files.copy(file.getInputStream(), targetPath);
        Picture picture = new Picture(0, tagname + "/" + fileName, tagid);
        pictureMapper.addPicture(picture);
        int pictureid = pictureMapper.getLastInsert();
        picture.setPictureid(pictureid);
        return picture;
    }
}
