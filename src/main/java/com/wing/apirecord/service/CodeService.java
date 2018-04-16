package com.wing.apirecord.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;

@Component
@Slf4j
public class CodeService {

    public   static final String  API_CLASS = "apiClass";
    public   static final String  API_DEF_GET = "apiDefGet";
    public   static final String  API_DEF_POST = "apiDefPost";
    public   static final String  API_METHOD_GET = "apiMethodGet";
    public   static final String  API_METHOD_POST = "apiMethodPost";

    public   static final String  METHOD = "method";

    private  static String userDir = System.getProperty("user.dir");


    public void updateCodeTemp(String codeTemp,String data) throws Exception{
        FileUtils.write(new File(userDir + "/codetemplates/"+codeTemp),data, Charset.forName("utf-8"));
    }

    public String codeTemp(String codeTemp) throws Exception{
       String result= FileUtils.readFileToString(new File(userDir + "/codetemplates/"+codeTemp), Charset.forName("utf-8"));
//       result=result.replace("<","&lt;");
//        result=result.replace(">","&gt;");
        return result;

    }


}
