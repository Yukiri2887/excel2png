package com.example.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.apache.log4j.PropertyConfigurator;
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

//        PropertyConfigurator.configure("/usr/local/spark-2.3.0-bin-hadoop2.7/conf/log4j.properties");
        SpringApplication.run(DemoApplication.class, args);
    }

}
