package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.City;
import com.example.demo.entity.Picture;
import com.example.demo.entity.Prediction;
import com.example.demo.entity.Tag;
import com.example.demo.entity.TagAndPicture;
import com.example.demo.entity.WeatherApi.WeatherNow;
import com.example.demo.entity.WeatherApi.WeatherResponse;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.mapper.TagMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import io.micrometer.core.instrument.util.IOUtils;

@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CityMapper cityMapper;

    @Autowired
    ObjectMapper objectMapper;

    private static String filePath = "/home/mdd/文档/fxs毕设/图片/";

    public Tag addTag(String tagname, int userid, String region) {
        Tag oldTag = tagMapper.getTagByName(tagname);
        if (oldTag != null) {
            return null;
        }
        String time = String.valueOf(System.currentTimeMillis());
        Tag tag = new Tag(0, tagname, userid, region, "", "", "", "", "", "", time);
        tagMapper.addTag(tag);
        int tagid = tagMapper.getLastInsert();
        tag.setTagid(tagid);
        return tag;
    }

    public List<TagAndPicture> getTagAndPictureByUserid(int userid) {
        List<Tag> tagList = tagMapper.getTagByUserid(userid);
        List<TagAndPicture> tagAndPictureList = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            List<Picture> pictureList = pictureMapper.getPictureByTagid(tag.getTagid());
            TagAndPicture tagAndPicture = new TagAndPicture(tag, pictureList);
            tagAndPictureList.add(tagAndPicture);
        }
        return tagAndPictureList;
    }

    public int getTagCountByUserid(int userid) {
        return tagMapper.getTagByUserid(userid).size();
    }

    public List<TagAndPicture> getTagAndPictureByRegion(String region) {
        List<Tag> tagList = tagMapper.getTagByRegion(region);
        List<TagAndPicture> tagAndPictureList = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            List<Picture> pictureList = pictureMapper.getPictureByTagid(tag.getTagid());
            TagAndPicture tagAndPicture = new TagAndPicture(tag, pictureList);
            tagAndPictureList.add(tagAndPicture);
        }
        return tagAndPictureList;
    }

    public int getTagCountByRegion(String region) {
        return tagMapper.getTagByRegion(region).size();
    }

    public Tag modTag(int tagid, String tagname, int userid, String region, String result, String tem, String hum,
            String win_dir, String win_speed, String pressure) {
        Tag tag = new Tag(tagid, tagname, userid, region, result, tem, hum, win_dir, win_speed, pressure, "");
        tagMapper.modTag(tag);
        return tag;
    }

    public TagAndPicture getTagAndPictureById(int tagid) {
        Tag tag = tagMapper.getTagById(tagid);
        List<Picture> pictureList = pictureMapper.getPictureByTagid(tagid);
        return new TagAndPicture(tag, pictureList);
    }

    public List<TagAndPicture> getAllTagAndPicture() {
        List<Tag> tagList = tagMapper.getAllTag();
        List<TagAndPicture> tagAndPictureList = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            List<Picture> pictureList = pictureMapper.getPictureByTagid(tag.getTagid());
            TagAndPicture tagAndPicture = new TagAndPicture(tag, pictureList);
            tagAndPictureList.add(tagAndPicture);
        }
        return tagAndPictureList;
    }

    public int getAllTagCount() {
        return tagMapper.getAllTag().size();
    }

    public String classifyTag(int tagid, String module) throws Exception {
        String url = "http://127.0.0.1:8082/predict_" + module;
        List<Picture> pictureList = pictureMapper.getPictureByTagid(tagid);
        ObjectMapper mapper = new ObjectMapper();
        Unirest.setTimeouts(0, 0);
        int[] a = { 0, 0, 0, 0 };
        int maxId = 0;
        for (int i = 0; i < pictureList.size(); i++) {
            Picture picture = pictureList.get(i);
            com.mashape.unirest.http.HttpResponse<String> response = Unirest.post(url)
                    .field("image", new File(filePath + picture.getPicture_path()))
                    .asString();
            String jsonString = response.getBody();
            Prediction prediction = mapper.readValue(jsonString, Prediction.class);
            int result = prediction.getPrediction();
            a[result]++;
            if (a[result] > a[maxId]) {
                maxId = result;
            }
        }
        if (maxId == 0) {
            return "阴天";
        } else if (maxId == 1) {
            return "多云";
        } else if (maxId == 2) {
            return "少云";
        } else {
            return "晴天";
        }
    }

    public Tag getWeather(int tagid) throws Exception {
        String key = System.getenv("WEATHER_API_KEY");
        Tag tag = tagMapper.getTagById(tagid);
        City city = cityMapper.getCityByName(tag.getRegion());
        String url = "https://devapi.qweather.com/v7/weather/now?";
        url += "location=" + city.getLocation_id();
        url += "&key=" + key;
        // 巨坑！！！这玩意返回的数据是gzip压缩的，花了好长时间才整出来这解压办法！！！
        byte[] response = restTemplate.getForEntity(url, byte[].class).getBody();
        GZIPInputStream gzipStream = new GZIPInputStream(new ByteArrayInputStream(response));
        String json = IOUtils.toString(gzipStream, StandardCharsets.UTF_8);
        WeatherResponse weatherResponse = new ObjectMapper().readValue(json,
                WeatherResponse.class);
        WeatherNow now = weatherResponse.getNow();
        tag.setTem(now.getTemp() + "°C");
        tag.setHum(now.getHumidity() + "%");
        tag.setWin_dir(now.getWindDir());
        tag.setWin_speed(now.getWindScale() + "级");
        tag.setPressure(now.getPressure() + "百帕");
        return tag;
    }
}
