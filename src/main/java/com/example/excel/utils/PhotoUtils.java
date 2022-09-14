package com.example.excel.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Component
public class PhotoUtils implements WebMvcConfigurer {

    //获取本地图片地址
//    @Value("${bmp.path:{null}}")
//    private String bmpPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 访问路径：http://localhost:8086/image/2022001.png
         * "/image/**" 为前端URL访问路径
         * "file:" + bmpPath 是本地磁盘映射
         */
        Properties p =  new Properties();
        FileInputStream fis2;
        {
            try {
                fis2 = new FileInputStream("src/url.properties");
                try {
                    p.load(fis2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        File tempPath = new File(p.getProperty("path"));
        if(!tempPath.exists() || !tempPath.isDirectory()) {
            tempPath.mkdir(); //如果不存在，则创建该文件夹
        }
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + p.getProperty("path"));
    }
}

