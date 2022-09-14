package com.example.excel.utils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class JsonUtils {

    /**
     * 判断是否为json字符串
     *
     * @param content
     * @return
     */
    public static boolean isJSONString(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        if (!content.startsWith("{") || !content.endsWith("}")) {
            return false;
        }
        try {
            JSONObject.parse(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}


