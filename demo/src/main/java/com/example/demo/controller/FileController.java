package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    private static String filePath = "/home/mdd/文档/fxs毕设/图片/";

    @GetMapping("/getFile")
    public ResponseEntity<byte[]> getFile(@RequestParam Map<String, String> map) throws Exception {
        String fileName = map.get("fileName");
        File file = new File(filePath + fileName);
        byte[] content = Files.readAllBytes(file.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // 修改为适合的图片类型
                .body(content);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("tagname") String tagname)
            throws Exception {
        String fileName = UUID.randomUUID().toString() + ".jpg";
        Path folderPath = Paths.get(filePath, tagname);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        Path targetPath = folderPath.resolve(fileName);
        Files.copy(file.getInputStream(), targetPath);
        return tagname + "/" + fileName;
    }
}
