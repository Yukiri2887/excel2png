package com.example.excel.service;

import com.example.excel.utils.DrawFromExcel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class excelToPngService {
    public  List<String> Convert(File file) throws IOException, InterruptedException {
        Properties p =  new Properties();
        FileInputStream fis2;
        {
            try {
                fis2 = new FileInputStream("src/url.properties");
                p.load(fis2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        List<String> returnList=new ArrayList<>();
        try {
            returnList= DrawFromExcel.excelToImage(file,p.getProperty("path"));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
