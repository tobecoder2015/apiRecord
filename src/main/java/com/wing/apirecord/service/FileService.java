package com.wing.apirecord.service;


import com.wing.apirecord.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.charset.Charset;


@Component
@Slf4j
public class FileService {

    public static String saveCodeRoot = ConfigUtil.getConfig("saveCodeRoot");
    public static String saveCodeBase = null;
    public static String saveCodeModule = ConfigUtil.getConfig("saveCodeModule");
    private static String saveCodeModuleData = null;
    private static String saveCodeModuleSchema = null;
    private static String saveCodeModuleTestsuites = null;
    private static String templatesRoot = null;
    private  static String userDir = System.getProperty("user.dir");


    public static final String API_CLASS = "apiClass";
    public static final String API_DEF_GET = "apiDefGet";
    public static final String API_DEF_POST = "apiDefPost";
    public static final String API_METHOD_GET = "apiMethodGet";
    public static final String API_METHOD_POST = "apiMethodPost";
    public static final String METHOD = "method";


    public static void setSavePath(String savePath){
        if(savePath!=null)
        saveCodeRoot = savePath;
        saveCodeBase=saveCodeRoot+"/src/test/java/"+saveCodeModule;
        saveCodeModuleData=saveCodeBase+"/data";
        saveCodeModuleSchema=saveCodeBase+"/schema";
        saveCodeModuleTestsuites=saveCodeBase+"/testsuites";
        templatesRoot=userDir+"/codetemplates/";

        try {
            FileUtils.forceMkdir(new File(saveCodeModuleData));
            FileUtils.forceMkdir(new File(saveCodeModuleSchema));
            FileUtils.forceMkdir(new File(saveCodeModuleTestsuites));
        }catch (Exception e){
            log.error("创建路径失败",e);
        }
    }

    public static void updateSavePath(){
        setSavePath(null);
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


    public String getApiDef() throws Exception{
        File file=new File(saveCodeBase + "/Api.java");
        if(file.exists()) {
            return  FileUtils.readFileToString(new File(saveCodeBase + "/Api.java"), Charset.forName("utf-8"));
        }else {
            return null;
        }
    }


    public boolean isExsit(String method) {
        File file=new File(saveCodeBase + "/Api.java");
        String content="";
        if(file.exists()) {
            try {
                content=  FileUtils.readFileToString(new File(saveCodeBase + "/Api.java"), Charset.forName("utf-8"));
            }catch (Exception e){
                log.error("读取api 文件失败",e);
            }
        }
        return content.toLowerCase().contains(method.toLowerCase()+"(");
    }

    public void writeApiDef(String data) throws Exception{
        FileUtils.write(new File(saveCodeBase + "/Api.java"),data, Charset.forName("utf-8"));
    }

    public String getTemplates(String template) {
        String  path=templatesRoot + template;
        try {
            File file = new File(path);
            if (file.exists()) {
                return FileUtils.readFileToString(new File(path), Charset.forName("utf-8"));
            } else {
                return null;
            }
        }catch (Exception e){
            log.error(path+" 代码模板文件不存在",e);
            return null;
        }
    }




}
