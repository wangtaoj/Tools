package com.waston.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
 * @author wangtao
 * Created on 2018/2/14
 **/
public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties properties = new Properties();

    static {
        String fileName = "redis.properties";
        try {
            //统一编码为UTF-8
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (Exception e) {
            logger.error("加载配置文件失败", e);
        }
    }


    public static String getProperty(String key) {
        String value =  properties.getProperty(key.trim());
        if(value == null || Objects.equals("", value.trim()))
            return null;
        return value.trim();
    }

    public static String getProperty(String key, String defaultValue) {
        String value =  properties.getProperty(key.trim());
        if(value == null || Objects.equals("", value.trim()))
            return defaultValue;
        return value.trim();
    }

}
