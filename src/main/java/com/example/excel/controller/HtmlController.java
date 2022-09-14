package com.example.excel.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.excel.entity.RespBody;
import com.example.excel.service.excelToPngService;
import com.example.excel.utils.Base2FileUtils;
import com.example.excel.utils.JsonUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class HtmlController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private JsonUtils jsonUtils;
    /**
     * pdf转ofd
     * @param InputJson
     */
    @RequestMapping(value="/ExcelToPng",method = RequestMethod.POST)
    public RespBody<Map<String, Object>> ExcelToPng(String InputJson) {
        LogLog.setInternalDebugging(true);
        Logger logger = Logger.getLogger(this.getClass());
        logger.info("开始调用接口");
        excelToPngService convertService = new excelToPngService();
        boolean isJson1 =jsonUtils.isJSONString(InputJson);
        if (InputJson.equals("") ||!isJson1) {
            try{
                if(!isJson1) {
                    return RespBody.fail(-1, "Json格式错误");
                }
                else {
                    return RespBody.fail(-1, "Json不能为空");
                }
            }catch (Exception e){
                return RespBody.fail(-1,"error");
            }
        }
        else {
            JSONObject json2 =JSONObject.fromObject(InputJson);//格式化成json对象
            String imgByte = json2.getString("imgByte");
            List<String> downList = new ArrayList<>();
                try {
                    logger.info("开始将base64转临时文件");
                    File waitConvert = Base2FileUtils.base64ToFile(imgByte);
                    logger.info("开始转换");
                    downList =  convertService.Convert(waitConvert);
                    logger.info("转换完成，返回http地址");
                } catch (IOException e) {
                    e.printStackTrace();
                    return RespBody.fail("读取文件失败");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return RespBody.fail("文件被占用");
                }
            Map<String, List<String>> map2 = new HashMap();
            map2.put("FileList", downList);
            String rList = JSON.toJSONString(map2, SerializerFeature.WriteMapNullValue);
            Map<String, Object> data = new HashMap<>();
            data.put("DownList", rList);
            return RespBody.data(data);
        }
    }
}