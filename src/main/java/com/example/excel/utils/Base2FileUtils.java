package com.example.excel.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Base64;
import java.util.Properties;

@Component
public class Base2FileUtils {
    /**
     * base64转化为file流
     * @param base64
     * @return
     */
    //获取本地图片地址
    public static File base64ToFile(String base64) {
        if (base64 == null || "".equals(base64)) {
            return null;
        }
        Properties p =  new Properties();
        FileInputStream fis2;
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

        byte[] buff = Base64.getDecoder().decode(base64);
        File file = null;
        FileOutputStream out = null;
        try {
            File tempPath = new File(p.getProperty("tempPath"));
            if(!tempPath.exists() || !tempPath.isDirectory()) {
                tempPath.mkdir(); //如果不存在，则创建该文件夹
            }
            file = File.createTempFile("tmp", null,tempPath);
            out = new FileOutputStream(file);
            out.write(buff);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        file.deleteOnExit();
        return file;
    }

}
