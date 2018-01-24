package com.wing.apirecord.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

@Slf4j
public class ConfigUtil {

    private  static Properties props=null;
    private  static String configFile="config.properties";
    private  static String userDir = System.getProperty("user.dir");

    static {
        try {
            props = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream(userDir +"/"+configFile));  //相当于加上“user.dir”的绝对路径
            props.load(in);
        }catch (Exception e){
            log.error("读取配置出错",e);
        }

    }


    public static String getConfig(String key){
        return (String) props.get(key);
    }

    public static void setConfig(String key,String value){
         props.setProperty(key,value);
    }


    public static void saveConfig(){
        try {
            OutputStream out = new FileOutputStream(userDir  +"/"+configFile);
            props.store(out,"update config");
        }catch (Exception e){
            log.error("更新配置错误",e);
        }
    }

    public static void main(String[] args) {
        log.info(ConfigUtil.getConfig("urls"));
        ConfigUtil.setConfig("urls","3027-mgtoo-sl-mta");
        ConfigUtil.saveConfig();
    }


}
