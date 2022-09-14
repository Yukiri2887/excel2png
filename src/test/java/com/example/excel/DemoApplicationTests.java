package com.example.excel;

import com.example.excel.utils.DrawFromExcel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        try {
            base64Encode();
//            String slnPath=System.getProperty("user.dir");
            DrawFromExcelTest.excelToImage("C:\\Users\\Machenike\\Desktop\\2022-09\\3.xls","C:\\Users\\Machenike\\Desktop\\2022-09");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String base64Encode() throws Exception {
        // 将文件转化为输入流
        String filePath = "C:\\Users\\Machenike\\Desktop\\2022-09\\4.xlsx";
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);

        // 将InputStream转化为byte[]
        // 如果使用byte[] byte = new byte[input.available()];这种方式会出现字节码全为0的情况，原因未知
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);

        byte[] b = new byte[1000];
        int n;
        //每次从fis读1000个长度到b中，fis中读完就会返回-1
        while ((n = inputStream.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        inputStream.close();
        bos.close();
        byte[] bytes = bos.toByteArray();

        // 进行Base64转码
        String base64Str = Base64.getEncoder().encodeToString(bytes);

        return base64Str;
    }
}
