package com.wing.apirecord.service;

import com.wing.apirecord.core.filter.ContentTypeFilter;
import com.wing.apirecord.core.filter.FilterChain;
import com.wing.apirecord.core.filter.MethodFilter;
import com.wing.apirecord.core.filter.UrlFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;

@Component
@Slf4j
public class FileService {

    public static String saveCodeRoot = "/Users/wangqingshan/app/hotel-bos-test";
    public static String saveCodeBase = null;
    public static String saveCodeModule = "priceTrace";
    public static String saveCodeModuleData = null;
    public static String saveCodeModuleSchema = null;
    public static String saveCodeModuleTestsuites = null;


    public void setSavePath(String savePath) throws Exception{
        saveCodeRoot = savePath;
        saveCodeBase=saveCodeRoot+"/src/test/java/"+saveCodeModule;
        saveCodeModuleData=saveCodeBase+"/data";
        saveCodeModuleSchema=saveCodeBase+"/schema";
        saveCodeModuleTestsuites=saveCodeBase+"/testsuites";

        FileUtils.forceMkdir(new File(saveCodeModuleData));
        FileUtils.forceMkdir(new File(saveCodeModuleSchema));
        FileUtils.forceMkdir(new File(saveCodeModuleTestsuites));

    }
    public void saveData(String fileName,String data) throws Exception {
        saveFile(saveCodeModuleData+"/"+fileName+".json",data);
    }

    public void saveSchema(String fileName,String data) throws Exception {
        saveFile(saveCodeModuleSchema+"/"+fileName+".json",data);
    }


    public void saveCodeModuleTestsuites(String fileName,String data) throws Exception {
        saveFile(saveCodeModuleTestsuites+"/"+fileName+".java",data);
    }

    public void saveFile(String savePath,String text) throws Exception{
        FileUtils.write(new File(savePath),text, Charset.forName("utf-8"));
    }


}
