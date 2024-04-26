package com.example.demo.entity.WeatherApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherNow {
    String temp;
    String windDir;
    String windScale;
    String humidity;
    String pressure;
}
